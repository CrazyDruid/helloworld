package Test.Config;

import javax.servlet.http.HttpServlet;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by Administrator on 2018/6/13.
 */

@ServerEndpoint(value = "/managerSocket")
public class ManagerServlet extends HttpServlet {

}
