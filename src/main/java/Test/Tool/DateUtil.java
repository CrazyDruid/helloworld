package Test.Tool;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/5/24.
 */
public class DateUtil {

    public static String getDateTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static String getDate(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
}
