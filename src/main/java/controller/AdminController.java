package controller;


import com.sun.org.glassfish.gmbal.ParameterNames;
import mapper.*;
import model.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.*;
import util.ExportExcel;
import util.MybatisSessionFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    private static Logger logger = Logger.getLogger(AdminController.class);
    private ModelAndView mav = new ModelAndView();

    @RequestMapping(value = "/admin_category_list",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView listCategory(HttpServletRequest request, HttpServletResponse response) throws Exception{
        List<Category> categories = categoryService.listAll();
        mav.addObject("thecs", categories);
        mav.setViewName("admin/listCategory");
        return mav;
    }

    @RequestMapping(value = "/admin_print_category_list",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView printListCategory(HttpServletRequest request, HttpServletResponse response) throws Exception{
        List<Category> categories = categoryService.listAll();
        mav.addObject("thecs", categories);
        mav.setViewName("admin/printListCategory");
        return mav;
    }

    @RequestMapping(value = "/admin_export_category_list",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void exportListCategory(HttpServletRequest request, HttpServletResponse response) throws Exception{
        List<Category> categories = categoryService.listAll();
        String []rowsName = new String[]{"id","name"};
        List<Object[]> dataList = new ArrayList<Object[]>();
        for(int i = 0;i < categories.size();i++){
            Object[] objs = new Object[rowsName.length];
            Category category = categories.get(i);
            objs[0] = category.getId();
            System.out.println(objs[0]);
            objs[1] = category.getName();
            System.out.println(objs[1]);
            dataList.add(objs);
        }
        String fileName = "exportCategoriesExcel";
        //执行导出
        ExportExcel.exportExcel(request,response,fileName, rowsName, dataList, "yyyy-MM-dd HH:mm:ss");
    }

    @RequestMapping(value = "/admin_order_list",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView listOrder(){
        List<Order> orders = orderService.listAll();
        for(Order order:orders){
            order.setOrderItems(orderItemService.getOrderItem(order.getId()));
        }
        mav.addObject("os",orders);
        mav.setViewName("/admin/listOrder");
        return mav;
    }

    @RequestMapping(value = "/admin_print_order_list",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView printListOrder(){
        List<Order> orders = orderService.listAll();
        for(Order order:orders){
            order.setOrderItems(orderItemService.getOrderItem(order.getId()));
        }
        mav.addObject("os",orders);
        mav.setViewName("/admin/printListOrder");
        return mav;
    }

    @RequestMapping(value = "/admin_export_order_list",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void exportListOrder(HttpServletRequest request, HttpServletResponse response) throws Exception{
        List<Order> orders = orderService.listAll();
        String []rowsName = new String[]{"ID", "状态", "金额", "房间数量","买家名称","支付时间"};
        List<Object[]> dataList = new ArrayList<Object[]>();
        for(int i = 0;i < orders.size();i++){
            Object[] objs = new Object[rowsName.length];
            Order order = orders.get(i);
            objs[0] = order.getId();
            objs[1] = order.getStatus();
            objs[2] = order.getTotal();
            objs[3] = order.getTotalNumber();
            objs[4] = order.getUser().getName();
            objs[5] = order.getPayDate();
            dataList.add(objs);
        }
        String fileName = "exportOrderExcel";
        //执行导出
        ExportExcel.exportExcel(request,response,fileName, rowsName, dataList, "yyyy-MM-dd HH:mm:ss");
    }

    @RequestMapping(value = "/admin_property_list",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView listProperty(@RequestParam(value = "cid") Integer cid,HttpServletRequest request){
        request.getSession().setAttribute("cid",cid);
        List<Property> properties = productService.listProproty(cid);
        mav.addObject("ps",properties);
        mav.setViewName("/admin/listProperty");
        return mav;
    }

    @RequestMapping(value = "/admin_property_edit",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_property_edit(@RequestParam(value = "id") Integer id){
        Property property = productService.getProperty(id);
        mav.addObject("p",property);
        mav.setViewName("/admin/editProperty");
        return mav;
    }

    @RequestMapping(value = "/admin_property_update",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_property_update(@RequestParam(value = "cid") Integer cid, @RequestParam(value = "id") Integer id,@RequestParam(value = "name") String name){
        List<Property> properties = productService.listProproty(cid);
        mav.addObject("ps",properties);
        mav.setViewName("/admin/listProperty");
        return mav;
    }

    @RequestMapping(value = "/admin_property_delete",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_property_delete(Integer id,HttpServletRequest request){
//        productService.deleteProperty(id);
        List<Property> properties = productService.listProproty((Integer)request.getSession().getAttribute("cid"));
        mav.addObject("ps",properties);
        mav.setViewName("/admin/listProperty");
        try{
            productService.deleteProperty(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return mav;
    }

    //房间管理
    @RequestMapping(value = "/admin_product_list",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_product_list(@RequestParam(value = "cid") Integer cid,HttpServletRequest request){
        List<Product> products = productService.listProducts(cid);
        mav.addObject("ps",products);
        mav.addObject("c",categoryService.getCategory(cid));
        mav.setViewName("/admin/listProduct");
        return mav;
    }

    @RequestMapping(value = "/admin_product_add",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_product_add(Product product,HttpServletRequest request){
        productService.insert(product);
        List<Product> products = productService.listProducts(product.getCid());
        mav.addObject("ps",products);
        mav.addObject("c",categoryService.getCategory(product.getCid()));
        mav.setViewName("/admin/listProduct");
        return mav;
    }

    @RequestMapping(value = "/admin_product_delete",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_product_delete(Integer id,Integer cid, HttpServletRequest request){
        productService.delete(id);
        List<Product> products = productService.listProducts(cid);
        mav.addObject("ps",products);
        mav.addObject("c",categoryService.getCategory(cid));
        mav.setViewName("/admin/listProduct");
        return mav;
    }

    @RequestMapping(value = "/admin_productImage_list",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_productImage_list(Integer pid, HttpServletRequest request){
        Product product = productService.getProduct(pid);
        product.setCategory(categoryService.getCategory(product.getCid()));
        List<Productimage> productimages = productService.listProductImage(product.getId());
        mav.addObject("p",product);
        mav.addObject("pisSingle",productimages);
        mav.addObject("pisDetail",productimages);
        mav.setViewName("/admin/listProductImage");
        return mav;
    }

    @RequestMapping(value = "/admin_product_edit",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_product_edit(Integer id, HttpServletRequest request){
        Product product = productService.getProduct(id);
        List<Propertyvalue> values = productService.listProprotyValue(id);
        for(Propertyvalue p: values){
            p.setProperty(productService.getProperty(p.getPtid()));
        }
        mav.addObject("p",product);
        mav.addObject("pvs",values);
        mav.setViewName("/admin/editProductValue");
        return mav;
    }

    @RequestMapping(value = "/admin_category_edit",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_category_edit(Integer id, HttpServletRequest request){
        Category category = categoryService.getCategory(id);
        mav.addObject("c",category);
        mav.setViewName("/admin/editCategory");
        return mav;
    }
    //用户管理
    @RequestMapping(value = "/admin_user_list",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView listUser(){
        List<User> users = userService.listAll();
        mav.addObject("us",users);
        mav.setViewName("/admin/listUser");
        return mav;
    }

    //打印用户信息
    @RequestMapping(value = "/admin_print_user_list",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView printLlistUser(){
        List<User> users = userService.listAll();
        mav.addObject("us",users);
        mav.setViewName("/admin/printListUser");
        return mav;
    }

    //导出用户信息
    @RequestMapping(value = "/admin_export_user_list",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void exportUserList(HttpServletRequest request, HttpServletResponse response) throws Exception{
        List<User> users = userService.listAll();
        String []rowsName = new String[]{"id","name","password"};
        List<Object[]> dataList = new ArrayList<Object[]>();
        for(int i = 0;i < users.size();i++){
            Object[] objs = new Object[rowsName.length];
            User user = users.get(i);
            objs[0] = user.getId();
            objs[1] = user.getName();
            objs[2] = user.getPassword();
            dataList.add(objs);
        }
        String fileName = "exportUserExcel";
        //执行导出
        ExportExcel.exportExcel(request,response,fileName, rowsName, dataList, "yyyy-MM-dd HH:mm:ss");
    }

    @RequestMapping(value = "/admin_user_edit",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_user_edit(Integer id, HttpServletRequest request){
        User user = userService.getUser(id);
        mav.addObject("u",user);
        mav.setViewName("/admin/editUser");
        return mav;
    }

    @RequestMapping(value = "/admin_user_delete",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_user_delete(Integer id, HttpServletRequest request){
        try{
            userService.deleteByPrimaryKey(id);
        }catch (Exception e){
            e.getMessage();
        }
        List<User> users = userService.listAll();
        mav.addObject("us",users);
        mav.setViewName("/admin/listUser");
        return mav;
    }


    //    need to fix it,it doesn't works
    @RequestMapping(value = "/admin_user_update",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_user_update(User user){
        user = userService.getUser(user.getName());
        userService.update(user);
        List<User> users = userService.listAll();
        mav.addObject("us",users);
        mav.setViewName("/admin/listUser");
        return mav;
    }

    @RequestMapping(value = "/admin_user_add",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_user_add(User user,HttpServletRequest request){
        userService.insert(user);
        List<User> users = userService.listAll();
        mav.addObject("us",users);
        mav.setViewName("/admin/listUser");
        return mav;
    }


    @RequestMapping(value = "/admin_order_edit",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_order_edit(Integer id, HttpServletRequest request){
        Order order = orderService.getOder(id);
        request.getSession().setAttribute("oid",id);
        mav.addObject("o",order);
        mav.setViewName("/admin/editOrder");
        return mav;
    }

    @RequestMapping(value = "/admin_order_update",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_order_update(@RequestParam(value = "id") Integer id,@RequestParam(value = "name") String name,@RequestParam(value = "password") String password){
        List<Order> orders = orderService.listAll();
        mav.addObject("os",orders);
        mav.setViewName("/admin/listOrder");
        return mav;
    }

    @RequestMapping(value = "/admin_order_add",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_order_add(Order order,HttpServletRequest request){
        request.getSession().setAttribute("id",order.getId());
        orderService.insert(order);
        List<Order> orders = orderService.listAll();
        mav.addObject("os",orders);
        mav.setViewName("/admin/listOrder");
        return mav;
    }

    @RequestMapping(value = "/admin_order_delete",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_order_delete(Integer id, HttpServletRequest request){
        orderService.deleteOrder(id);
        List<Order> orders = orderService.listAll();
        mav.addObject("os",orders);
        mav.setViewName("/admin/listOrder");
        return mav;
    }

    @RequestMapping(value = "/admin_category_delete",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_category_delete(Integer id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            categoryService.delete(id);
        } catch (Exception e) {
            e.getMessage();
        }
        return listCategory(request, response);
    }
}
