package Test.Controller;

import Test.Annotation.MyInfoAnnotation;
import Test.Entity.User;
import Test.Service.RedisService;
import Test.Service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/8/16.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    static final Logger logger = LogManager.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;

    @ResponseBody
    @RequestMapping(value = "/add")
    public int addUser(User user){
        return userService.addUser(user);
    }

    @RequestMapping(value = "/index")
    public String index(){
        return "user/index";
    }

    @RequestMapping(value = "/newTest")
    public String test(){
        return "user/newTest";
    }

    @RequestMapping(value = "/movie")
    public String movieTest(){
        return "user/movieTest";
    }

    @ResponseBody
    @RequestMapping(value = "/getUser")
    public String getUser(HttpServletRequest req){
        logger.info("获取用户");
        String id = req.getParameter("id");
        User user =  userService.getUser(id);
        if (user == null) return "无此用户";
        else return user.getName();
    }

    @ResponseBody
    @RequestMapping(value = "/getRedis")
    public String getRedis(HttpServletRequest req){
        String id = req.getParameter("id");
        if (id == null) return "没有id";
        User user =  (User)redisService.get(id);
        if (user == null) return "无此用户";
        else return user.toString();
    }

    @RequestMapping(value = "/getTestXSS")
    public String getTestXSS(){
        return "user/testXSS";
    }


    @MyInfoAnnotation(value = "FCC")
    @RequestMapping("/testAnn")
    @ResponseBody
    public String testBusiness(){
        return "business";
    }



//    @ResponseBody
//    @RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
//    public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
//
//        return userService.findAllUser(pageNum,pageSize);
//    }
}
