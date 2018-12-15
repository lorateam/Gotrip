package controller;


import model.*;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.*;
import util.ExportExcel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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


    private ModelAndView listProduct(Integer cid) {
        List<Product> products = productService.listProducts(cid);
        mav.addObject("ps",products);
        mav.addObject("c",categoryService.getCategory(cid));
        mav.setViewName("/admin/listProduct");
        return mav;
    }

    //查询所有房间分类
    @RequestMapping(value = "/admin_category_list",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView listCategory(){
        List<Category> categories = categoryService.listAll();
        mav.addObject("thecs", categories);
        mav.setViewName("admin/listCategory");
        return mav;
    }

    //打印页面
    @RequestMapping(value = "/admin_print_category_list",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView printListCategory(){
        List<Category> categories = categoryService.listAll();
        mav.addObject("thecs", categories);
        mav.setViewName("admin/printListCategory");
        return mav;
    }

    //导出分类EXCEL文件
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

    //添加分类
    //特别注意，由于本操作涉及图片保存，更换运行环境是需要重新配置路径
    @RequestMapping(value = "/admin_category_add",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_category_add(@RequestParam("name") String name, @RequestParam("filepath") MultipartFile filepath) throws IOException {
        Category category = new Category();
        category.setName(name);
        Integer cid = categoryService.insert(category);
        String path1 = "C:\\Users\\70953\\github\\Gotrip\\src\\main\\webapp\\img\\category\\"+cid.toString()+".jpg";
        String path2 = "C:\\Users\\70953\\github\\Gotrip\\target\\Gotrip\\img\\category\\"+cid.toString()+".jpg";
        filepath.transferTo(new File(path1));
        FileUtils.copyFile(new File(path1),new File(path2));
        mav = listCategory();
        return mav;
    }

    //查询所有订单
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
        mav = listOrder();
        mav.setViewName("/admin/printListOrder");
        return mav;
    }

    //导出订单列表
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

    //查询所有属性
    @RequestMapping(value = "/admin_property_list",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView listProperty(@RequestParam(value = "cid") Integer cid,HttpServletRequest request){
        request.getSession().setAttribute("cid",cid);
        List<Property> properties = productService.listProproty(cid);
        mav.addObject("ps",properties);
        mav.addObject("c",categoryService.getCategory(cid));
        mav.setViewName("/admin/listProperty");
        return mav;
    }

    //编辑属性名称页面
    @RequestMapping(value = "/admin_property_edit",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_property_edit(@RequestParam(value = "id") Integer id){
        Property property = productService.getProperty(id);
        mav.addObject("p",property);
        mav.setViewName("/admin/editProperty");
        return mav;
    }

    //提交编辑的属性名称
    @RequestMapping(value = "/admin_property_update",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_property_update(Property record){
        productService.updateProperty(record);
        List<Property> properties = productService.listProproty(record.getCid());
        mav.addObject("ps",properties);
        mav.setViewName("/admin/listProperty");
        return mav;
    }

    //删除属性名称
    @RequestMapping(value = "/admin_property_delete",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_property_delete(Integer id,HttpServletRequest request){
        try{
            productService.deleteProperty(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        List<Property> properties = productService.listProproty((Integer)request.getSession().getAttribute("cid"));
        mav.addObject("ps",properties);
        mav.setViewName("/admin/listProperty");
        return mav;
    }

    //房间管理
    @RequestMapping(value = "/admin_product_list",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_product_list(Integer cid){
        return listProduct(cid);
    }

    //添加房间
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

    //删除房间
    @RequestMapping(value = "/admin_product_delete",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_product_delete(Integer id,Integer cid, HttpServletRequest request){
        productService.delete(id);
        return listProduct(cid);
    }

    //房间图片管理页面
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

    //更改房间属性选项
    @RequestMapping(value = "/admin_product_editPropertyValue",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_product_editPropertyValue(Integer id, HttpServletRequest request){
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

    //提交更新房间属性值
    @RequestMapping(value = "/admin_product_updatePropertyValue",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String admin_product_updatePropertyValue(Integer id, Integer pvid, String value){
        productService.updatePropertyValue(pvid, value);
        return "success";
    }

    //提交房间修改信息
    @RequestMapping(value = "/admin_product_update",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_product_update(Product product){
        productService.update(product);
        return admin_product_list(product.getCid());
    }

    //编辑房间信息页面
    @RequestMapping(value = "/admin_product_edit",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_product_edit(Integer id, HttpServletRequest request){
        Product product = productService.getProduct(id);
        mav.addObject("p",product);
        mav.setViewName("/admin/editProduct");
        return mav;
    }

    //编辑房间分类页面
    @RequestMapping(value = "/admin_category_edit",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_category_edit(Integer id, HttpServletRequest request){
        Category category = categoryService.getCategory(id);
        mav.addObject("c",category);
        mav.setViewName("/admin/editCategory");
        return mav;
    }

    //查询所有用户
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


    //删除用户
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

    //编辑用户信息页面
    @RequestMapping(value = "/admin_user_edit",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_user_edit(Integer id, HttpServletRequest request){
        User user = userService.getUser(id);
        mav.addObject("u",user);
        mav.setViewName("/admin/editUser");
        return mav;
    }

    //提交更新用户信息
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

    //添加用户
    @RequestMapping(value = "/admin_user_add",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_user_add(User user,HttpServletRequest request){
        userService.insert(user);
        List<User> users = userService.listAll();
        mav.addObject("us",users);
        mav.setViewName("/admin/listUser");
        return mav;
    }


    //修改订单信息页面
    @RequestMapping(value = "/admin_order_edit",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_order_edit(Integer id, HttpServletRequest request){
        Order order = orderService.getOrder(id);
        request.getSession().setAttribute("oid",id);
        mav.addObject("c",order);
        mav.setViewName("/admin/editOrder");
        return mav;
    }

    //条件查询订单
    @RequestMapping(value = "/select_order_list",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView select_order_list(@RequestParam("status") String status, @RequestParam("min") Integer min, @RequestParam("max") Integer max){
        if(status==null || status.equals("")){
            status = "%";
        }
        if(min==null){
            min = -1;
        }
        if(max==null){
            max = Integer.MAX_VALUE;
        }
        List<Order> orders = orderService.selectOrder(status,min,max);
        for(Order order:orders){
            order.setOrderItems(orderItemService.getOrderItem(order.getId()));
        }
        mav.addObject("os",orders);
        mav.setViewName("/admin/listOrder");
        return mav;
    }

    //提交修改订单信息
    @RequestMapping(value = "/admin_order_update",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_order_update(Order order){
        orderService.update(order);
        mav = listOrder();
        mav.setViewName("/admin/listOrder");
        return mav;
    }

    //超时三天未评论的订单
    @RequestMapping(value = "/outdate_order",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView outdate_order(){
        List<Order> orders = orderService.outDateOrder();
        mav.addObject("os",orders);
        mav.setViewName("/admin/listOrder");
        return mav;
    }

    //添加订单
    @RequestMapping(value = "/admin_order_add",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_order_add(Order order,HttpServletRequest request){
        request.getSession().setAttribute("id",order.getId());
        orderService.insert(order);
        mav = listOrder();
        mav.setViewName("/admin/listOrder");
        return mav;
    }

    //删除订单
    @RequestMapping(value = "/admin_order_delete",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_order_delete(Integer id){
        orderService.deleteOrder(id);
        mav = listOrder();
        mav.setViewName("/admin/listOrder");
        return mav;
    }

    //删除房间分类
    @RequestMapping(value = "/admin_category_delete",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_category_delete(Integer id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            categoryService.delete(id);
        } catch (Exception e) {
            e.getMessage();
        }
        return listCategory();
    }

    //添加属性
    @RequestMapping(value = "/admin_property_add",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView admin_property_add(Property property) throws Exception {
        productService.newProperty(property);
        List<Property> properties = productService.listProproty(property.getCid());
        mav.addObject("ps",properties);
        mav.setViewName("/admin/listProperty");
        return mav;
    }

}
