package controller;

import model.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@Controller
@RequestMapping("")
public class GeneralController {
    private static Logger logger = Logger.getLogger(OrderController.class);
    private ModelAndView modelAndView = new ModelAndView();

    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;
    @Autowired
    ReviewService reviewService;

    @RequestMapping(value = "/forelogin",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView logIn(HttpServletRequest request, HttpServletResponse response,String name, String password) throws Exception{
        User user = userService.getUser(name);
        if(user != null && user.getPassword().equals(password)){
            //登陆成功
            request.getSession(true).setAttribute("user",user);
            logger.info(request.getPathInfo());
            return home(request);
        }
        return null;
    }

    @RequestMapping(value = "/forelogout",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void logOut(HttpServletRequest request, HttpServletResponse response) throws Exception{
        request.getSession().invalidate();
        response.sendRedirect("home.jsp");
    }

    @RequestMapping(value = "/foresearch",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public  ModelAndView search(HttpServletRequest request, HttpServletResponse response, String keyword) throws Exception{
        List<Product> ps = productService.listProducts(keyword);

        modelAndView.addObject("ps",ps);
        modelAndView.setViewName("/searchResult");
        return modelAndView;
    }

    @RequestMapping(value = "/foreproduct",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public  ModelAndView product(Integer pid) throws Exception{
        Product p = productService.getProduct(pid);
        List<Propertyvalue> pvs = productService.listProprotyValue(p.getId());
        modelAndView.addObject(pvs);
        modelAndView.addObject("p", p);
        modelAndView.setViewName("/product");
        return modelAndView;
    }

    @RequestMapping(value = "/forecheckLogin", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public int check(HttpServletRequest request) throws Exception{
        User user = (User) request.getSession().getAttribute("user");
        if(user != null)
            return 1;
        return 0;
    }

    @RequestMapping(value = "/forebuyone",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public  ModelAndView forebuyone(HttpServletRequest request, Orderitem orderitem) throws Exception{
        User user = (User) request.getSession().getAttribute("user");
        Map map =  orderItemService.buyOne(user, orderitem);
        request.getSession(true).setAttribute("order",map.get("order"));
        request.getSession(true).setAttribute("pid",orderitem.getPid());
        Product product = productService.getProduct(orderitem.getPid());
        product.setSaleCount(product.getSaleCount()+orderitem.getNumber());
        product.setStock(product.getStock()-orderitem.getNumber());
        productService.update(product);
        modelAndView.addObject("ois",map.get("ois"));
        modelAndView.addObject("total",((Order)map.get("order")).getTotal());
        modelAndView.setViewName("/buy");
        return modelAndView;
    }



    @RequestMapping(value = "/home",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView home(HttpServletRequest request) throws  Exception{
        List<Category> c = categoryService.listTwoCategory();
        for(Category cs: c){
            cs.setProducts(productService.listProducts(cs.getId()));
        }
        modelAndView.addObject("cs",c);
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value = "/forecreateOrder",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView buy(Order order, HttpServletRequest request) throws  Exception{
        /**确定支付**/
        Order origin_order = (Order)request.getSession().getAttribute("order");
        origin_order.setOrderCode(UUID.randomUUID().toString().replace("-", "").toLowerCase());
        origin_order.setReceiver(order.getReceiver());
        origin_order.setMobile(order.getMobile());
        origin_order.setUserMessage(order.getUserMessage());
        origin_order.setStatus("waitReview");
        origin_order.setPayDate(new Date());
        if(order.getReceiver()==null || order.getReceiver().equals("")){
            User user = (User)request.getSession().getAttribute("user");
            order.setReceiver(user.getName());
        }
        orderService.update(origin_order);
        modelAndView.addObject("o",origin_order);
        modelAndView.setViewName("/confirmPay");
        return modelAndView;
    }

    @RequestMapping(value = "/foreorderConfirmed", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public ModelAndView confirm() throws Exception{
        modelAndView.setViewName("orderConfirmed");
        return modelAndView;
    }

    @RequestMapping(value = "/forebought", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public ModelAndView bought(HttpServletRequest request) throws Exception{
        modelAndView.setViewName("bought");
        User user = (User) request.getSession().getAttribute("user");
        modelAndView.addObject("os", orderService.getOrders(user));
        return modelAndView;
    }

    @RequestMapping(value = "/deleteorder", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public void deleteorder(HttpServletRequest request,HttpServletResponse response, Integer oid) throws Exception{
        orderService.deleteOrder(oid);
        response.sendRedirect("forebought");
    }

    @RequestMapping(value = "/forestory", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public ModelAndView story()throws Exception{
        modelAndView.setViewName("story");
        return modelAndView;
    }

    @RequestMapping(value = "/forereview", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public ModelAndView review(int oid, HttpServletRequest request)throws Exception{
        Order order = orderService.getOrder(oid);
        Product product = productService.getProduct(orderItemService.getOrderItem(oid).get(0).getPid());
        List<Review> reviews = reviewService.listReview(product.getId());
        modelAndView.addObject("re", reviews);
        modelAndView.addObject("p",product);
        modelAndView.addObject("o",order);
        modelAndView.addObject("u",request.getSession().getAttribute("user"));
        modelAndView.setViewName("review");
        return modelAndView;
    }

    @RequestMapping(value = "/foredoreviewed", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public ModelAndView reviewed(Review review, HttpServletRequest request)throws Exception{
        reviewService.insert(review);
        Product product = productService.getProduct(review.getPid());
        product.setReviewCount(product.getReviewCount()+1);
        productService.update(product);
        Order order = orderService.getOrder(review.getOid());
        order.setStatus("reviewed");
        orderService.update(order);
        return review(review.getOid(),request);
    }
}
