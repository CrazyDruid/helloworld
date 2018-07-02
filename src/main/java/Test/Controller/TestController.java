package Test.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2018/6/11.
 */

@Controller
@RequestMapping("/super")
public class TestController {

    @RequestMapping("/testSuperSocket")
    public String testSuperSocket(){
        return "user/super";
    }
}
