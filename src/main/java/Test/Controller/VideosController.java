package Test.Controller;

import Test.Service.VideosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Administrator on 2018/5/24.
 */

@Controller
@RequestMapping(value = "/video")
public class VideosController {

    @Autowired
    private VideosService videosService;

    @ResponseBody
    @RequestMapping(value = "/getBili")
    public String getBili(HttpServletRequest req){
//        String status = req.getParameter("status");
        int count = 0;
        try {
            count = videosService.getVideo();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (count != 0){
            return "共" + String.valueOf(count) + "条数据";
        }else {
            return "没有数据";
        }
    }
}
