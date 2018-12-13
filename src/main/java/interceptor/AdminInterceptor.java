package interceptor;

import controller.AdminController;
import model.User;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminInterceptor implements HandlerInterceptor {
    private static Logger logger = Logger.getLogger(AdminController.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        logger.info("执行spring拦截器");
        //检查当前sessions是否有登录用户
        User user = (User) request.getSession().getAttribute("user");
        if(user != null)
            return true;
        else
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        return false;
    }

    @Override
    public void postHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        logger.info("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws  Exception{
        logger.info("spring拦截结束执行");
    }
}
