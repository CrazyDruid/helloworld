package Test.Service;

import Test.Dao.VideosMapper;
import Test.Entity.Videos;
import Test.Tool.DateUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

/**
 * Created by Administrator on 2018/5/24.
 */

@Service
public class VideosService {

    @Autowired
    private VideosMapper videosMapper;


    public int getVideo() throws IOException {
        int i = 0;
        int page = 1;
        String url = "https://www.bilibili.com/v/game/stand_alone/#/all/default/0/" + page + "/";
        while (true){
            if (i == 100) return i;
            Runtime rn = Runtime.getRuntime();
            Process process = rn.exec("E:/PhantomJS/phantomjs-2.1.1-windows/bin/phantomjs e:/html/hello.js " + url);
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String temp = "";
            while((temp=br.readLine())!=null){
                sb.append(temp);
            }
            String body = sb.toString();
            Document doc = Jsoup.parseBodyFragment(body);
            Elements elements = doc.getElementsByClass("r");
            if (elements.size() != 0){
                for (Element element : elements) {
                    i++;
                    String name = element.select("a").get(0).attr("title");
                    String videoUrl = "https:" + element.select("a").get(0).attr("href");
                    String author = element.select("div.up-info").get(0).select("a").get(0).attr("title");
                    String synopsis = element.select("div.v-desc").get(0).html();
                    String count1 = element.select("span.v-info-i").get(0).html();
                    String count2 = element.select("span.v-info-i").get(1).html();
                    count1 = Jsoup.parseBodyFragment(count1).select("span").get(0).html();
                    count2 = Jsoup.parseBodyFragment(count2).select("span").get(0).html();
                    String counts = count1 + "/" + count2;
                    Videos vs = videosMapper.findByName(name);
                    if (vs == null){
                        vs = new Videos();
                        vs.setId(UUID.randomUUID().toString());
                        vs.setName(name);
                        vs.setAuthor(author);
                        vs.setSynopsis(synopsis);
                        vs.setUrl(videoUrl);
                        vs.setCounts(counts);
                        vs.setCreateDate(DateUtil.getDateTime());
                        vs.setDelFlag("0");
                        videosMapper.insert(vs);
                    }else {
                        vs.setAuthor(author);
                        vs.setSynopsis(synopsis);
                        vs.setUrl(videoUrl);
                        vs.setCounts(counts);
                        vs.setUpdateDate(DateUtil.getDateTime());
                        videosMapper.updateByPrimaryKeyWithBLOBs(vs);
                    }
                }
            }
            page += 1;
            url = "https://www.bilibili.com/v/game/stand_alone/#/all/default/0/" + page + "/";
        }
    }

}
