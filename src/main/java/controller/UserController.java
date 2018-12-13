package controller;


import mapper.OrderMapper;
import mapper.UserMapper;
import model.Order;
import model.OrderExample;
import model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;
import util.MybatisSessionFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
    private static Logger logger = Logger.getLogger(OrderController.class);
    @Autowired
    UserService userService;

    @RequestMapping(value = "/foreregister",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response,User user) throws Exception{
        GeneralController generalController = new GeneralController();
        ModelAndView mav = new ModelAndView();
        try{
            userService.insert(user);
            request.getSession().setAttribute("user",user);
        }catch (Exception e){
            logger.info("用户名已存在");
            return generalController.home(request);
        }
        mav.setViewName("registerSuccess");
        return mav;
    }
}
