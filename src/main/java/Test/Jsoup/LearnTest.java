package Test.Jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.util.UUID;

/**
 * Created by cheng on 2018/5/16.
 */
public class LearnTest {

    public static void main(String[] args) throws IOException {
//        zhihuJsoup();
//        txtPrint();
//        getImg();
//        getGoldDream();

    }

    //...goldDream
    public static void getGoldDream() throws IOException {
        Runtime rn = Runtime.getRuntime();
        String url = "http://webpc.igoldendream.com/#/login";
        Process process = rn.exec("E:/PhantomJS/phantomjs-2.1.1-windows/bin/phantomjs e:/html/hello.js " + url);
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuffer sb = new StringBuffer();
        String temp = "";
        while((temp=br.readLine())!=null){
            sb.append(temp);
        }
        String body = sb.toString();
        Document doc = Jsoup.parseBodyFragment(body);
        System.out.println(doc);
    }

    //请求网页
    public static Document getDoc(String url){
        Document document = null;
        try {
            document = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.170 Safari/537.36")
                    .timeout(5000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }


    //获取网站图片
    public static void getImg() throws IOException{
        String url = "https://www.baidu.com";
        Document document = getDoc(url);
        Elements imgElements = document.select("img[src]");
        System.out.println(imgElements.size());
        FileOutputStream fos = null;
        InputStream is = null;
        BufferedInputStream bis = null;
        if (imgElements != null){
            for (Element element : imgElements) {
                String name = UUID.randomUUID().toString().replaceAll("-","") + ".jpg";
                URL imgUrl = new URL(element.absUrl("src"));
                is = imgUrl.openConnection().getInputStream();
                bis = new BufferedInputStream(is);
                byte[] byteArray = new byte[1024];
                fos = new FileOutputStream("E:\\ImgList\\" + name);
                int size = 0;
                while ((size = bis.read(byteArray)) != -1){
                    fos.write(byteArray,0,size);
                }
                fos.flush();
            }
        }else {
            System.out.println("NOT FOUND");
        }
        if (is != null){
            is.close();
        }
        if (fos != null){
            fos.close();
        }
        if (bis != null){
            bis.close();
        }
    }

    //获取小说文本
    public static void txtPrint() throws IOException {
        File file = new File("D:\\测试小说.txt");
        FileWriter fw = null;
        fw = new FileWriter(file);
        String url = "http://www.jueshitangmen.info/wudongqiankun/2.html";
        int i = 1;
        while (true){
            if (i == 11) break;
            Document doc = getDoc(url);
            Element title = doc.getElementsByClass("bg").get(0).select("h1").get(0);
            System.out.println(title.text());
            Elements pElement = doc.getElementsByClass("content").get(0).select("p");
            Elements pTurn = pElement.get(pElement.size()-1).select("a[href]");
            fw.write(title.text());
            fw.write("\r\n");
            fw.write(pElement.text().replaceAll("\\s+",""));
            fw.write("\r\n");
            fw.flush();
            System.out.println("写入第" + String.valueOf(i) + "章");
            i++;
            url = pTurn.attr("href");
            System.out.println(url);
        }
        fw.close();
    }


    //获取知乎编辑页面
    public static void zhihuJsoup() throws IOException {
        //获取编辑推荐页
        String urls = "https://www.zhihu.com/explore/recommendations";
        Document document= getDoc(urls);
        System.out.println(document.body());
        Element main=document.getElementById("zh-recommend-list-full");

        Elements url=main.select("div").select("div:nth-child(2)")
                .select("h2").select("a[class=question_link]");
        System.out.println(url.size());
        for(Element question:url){
            //输出href后的值，即主页上每个关注问题的链接
            String URL=question.attr("abs:href");
            //下载问题链接指向的页面
            Document document2=Jsoup.connect(URL)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.170 Safari/537.36")
                    .get();
            //标签
            Element detail = document2.getElementsByClass("QuestionPage").get(0).select("meta").get(2);
            Element ccc = document2.select("span.RichText.ztext").get(0);
//            System.out.println(ccc.toString());
            System.out.println("\n"+"链接："+URL
                    +"\n"+"标题："+ question.text()
                    +"\n"+"标签："+detail.attr("content")
                    +"\n"+"描述：" + ccc.text());
        }
    }
}
