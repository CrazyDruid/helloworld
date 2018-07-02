package Test.Jsoup;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

/**
 * Created by Administrator on 2018/1/15 0015.
 */
public class TestSelenium {

    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver","D:\\Documents\\chromedriver.exe");//chromedriver鏈嶅姟鍦板潃
        ChromeOptions options = new ChromeOptions();
        WebDriver driver =new ChromeDriver(options);

        driver.get("http://webpc.igoldendream.com/#/login");
        driver.findElement(By.xpath("//input[@type='num']")).sendKeys(new  String("13614328008"));
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(new  String("123456"));
        driver.findElement(By.className("login-submit")).click();
        randomSleep(3);

        Cookie cookie1 = driver.manage().getCookieNamed("schoolId");
        Cookie cookie2 = driver.manage().getCookieNamed("jinPhone");
        Cookie cookie3 = driver.manage().getCookieNamed("shenqingDataStatus");
        System.out.println(cookie1.getValue());
        System.out.println(cookie2.getValue());
        System.out.println(cookie3.getValue());
        Thread.sleep(3000);
        driver.close();

//        System.getProperties().setProperty("http.proxyHost", "123.103.93.38");
//        System.getProperties().setProperty("http.proxyPort", "80");
        //纭畾浠ｇ悊鏄惁璁剧疆鎴愬姛
//        System.out.println(getHtml("http://www.ip138.com/ip2city.asp"));

//        options.addArguments("referer=http://www.qichacha.com/");
//        options.addArguments("disable-infobars");
//        options.addArguments("user-data-dir=C:\\Users\\Administrator\\AppData\\Local\\Google\\Chrome\\User Data\\Default");

//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        ArrayList<String> switches = new ArrayList<String>();
//        switches.add("--proxy-server=localhost:8080");
//        switches.add("--referer=http://www.qichacha.com/");
//        capabilities.setCapability("chrome.switches", switches);
        //鏂板缓涓�涓猈ebDriver 鐨勫璞★紝浣嗘槸new 鐨勬槸FirefoxDriver鐨勯┍鍔�
//        driver.get("http://www.qichacha.com/user_login");//鎵撳紑鎸囧畾鐨勭綉绔�
        //鎵撳紑鎸囧畾

//        driver.get("http://web2.qq.com/");//鎵撳紑鎸囧畾鐨勭綉绔檋ttp://web2.qq.com/
//        int target = 308;
//        int xOffset = 1;
//        int yOffset = 1;
//        int max = 0;
        //闅忔満绛夊緟
//        driver.findElement(By.name("nameNormal")).click();



//        WebElement dragger = driver.findElement(By.className("nc-lang-cnt"));
////        WebElement dragger = driver.findElement(By.className("btn_slide"));
//        Actions action = new Actions(driver);
//        action.clickAndHold(dragger).build().perform();
//
//        for (int i = 0; i < target; i+=xOffset) {
//            xOffset = new Random().nextInt(10)+10;
//            yOffset = new Random().nextInt(3)+1;
//            System.out.println(" x:"+xOffset+" y:"+yOffset);
//            max = xOffset + i;
//            if(max > target){
//                xOffset = target - i;
//                System.out.println("max:"+max+" target:"+target+" i:"+i+" xOffset:"+xOffset);
//                break;
//            }
//            action.moveByOffset(xOffset, yOffset).perform();
////                action.moveByOffset(offset, 0).perform();
////                action.moveByOffset(xOffset, yOffset).perform();
//        }
//        action.release();
        /**
         * WebDriver鑷甫浜嗕竴涓櫤鑳界瓑寰呯殑鏂规硶銆�
         dr.manage().timeouts().implicitlyWait(arg0, arg1锛夛紱
         Arg0锛氱瓑寰呯殑鏃堕棿闀垮害锛宨nt 绫诲瀷 锛�
         Arg1锛氱瓑寰呮椂闂寸殑鍗曚綅 TimeUnit.SECONDS 涓�鑸敤绉掍綔涓哄崟浣嶃��
         */
//        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//        鎻愪氦
//        driver.findElement(By.className("login-btn")).click(); //鐐瑰嚮鎸夋壄
        /**
         * dr.quit()鍜宒r.close()閮藉彲浠ラ��鍑烘祻瑙堝櫒,绠�鍗曠殑璇翠竴涓嬩袱鑰呯殑鍖哄埆锛氱涓�涓猚lose锛�
         * 濡傛灉鎵撳紑浜嗗涓〉闈㈡槸鍏充笉骞插噣鐨勶紝瀹冨彧鍏抽棴褰撳墠鐨勪竴涓〉闈€�傜浜屼釜quit锛�
         * 鏄��鍑轰簡鎵�鏈塛ebdriver鎵�鏈夌殑绐楀彛锛岄��鐨勯潪甯稿共鍑�锛屾墍浠ユ帹鑽愪娇鐢╭uit鏈�涓轰竴涓猚ase閫�鍑虹殑鏂规硶銆�
         */
//        Cookie qddagsx = driver.manage().getCookieNamed("_qddagsx_02095bad0b");
//        System.out.println(qddagsx.getValue());
//        Thread.sleep(60 * 1000);
//        driver.quit();//閫�鍑烘祻瑙堝櫒
    }

//    public static void main(String[] args) {
//        //濡傛灉鐏嫄娴忚鍣ㄦ病鏈夐粯璁ゅ畨瑁呭湪C鐩橈紝闇�瑕佸埗瀹氬叾璺緞
////        System.setProperty("webdriver.firefox.bin", "D:/FireFox/firefox.exe");
//
//        File pathToBinary = new File("D:\\FireFox\\firefox.exe");
//        FirefoxBinary firefoxBinary = new FirefoxBinary(pathToBinary);
//        FirefoxProfile firefoxProfile = new FirefoxProfile();
//        System.setProperty("webdriver.gecko.driver", "D:\\apply\\geckodriver-v0.19.1-win64\\geckodriver.exe");
//        WebDriver driver = new FirefoxDriver(firefoxBinary, firefoxProfile);
//        driver.get("http://www.qichacha.com/user_login");
//        try {
//            Thread.sleep(60 * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        driver.close();//鍏抽棴椹卞姩
//
//    }

    private static String getHtml(String address){
        StringBuffer html = new StringBuffer();
        String result = null;
        try{
            URL url = new URL(address);
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36");
            BufferedInputStream in = new BufferedInputStream(conn.getInputStream());

            try{
                String inputLine;
                byte[] buf = new byte[4096];
                int bytesRead = 0;
                while (bytesRead >= 0) {
                    inputLine = new String(buf, 0, bytesRead, "ISO-8859-1");
                    html.append(inputLine);
                    bytesRead = in.read(buf);
                    inputLine = null;
                }
                buf = null;
            }finally{
                in.close();
                conn = null;
                url = null;
            }
            result = new String(html.toString().trim().getBytes("ISO-8859-1"), "gb2312").toLowerCase();

        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            html = null;
        }
        return result;
    }

    private static void randomSleep(int maxSecond) {
        int sleep = new Random().nextInt(maxSecond) +1;
        try {
            Thread.sleep(sleep * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
