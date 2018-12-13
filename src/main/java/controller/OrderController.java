package controller;


import mapper.OrderMapper;
import model.Order;
import model.OrderExample;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.MybatisSessionFactory;

import java.util.List;


@Controller
@RequestMapping("/order")
class OrderController {

    private static Logger logger = Logger.getLogger(OrderController.class);
    //获取所有订单
    @RequestMapping(value = "/list",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public List<Order> listOrder(/* @RequestParam(value = "addressId") int addressId */) throws Exception{
        SqlSession sqlSession = MybatisSessionFactory.getSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        return orderMapper.selectAll();
    }

    @RequestMapping(value = "/deleteOne",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deleteById(@RequestParam(value = "orderId") Integer orderId) throws Exception{
        SqlSession sqlSession = MybatisSessionFactory.getSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        try{
            orderMapper.deleteByPrimaryKey(orderId);
            return "删除成功";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/delete",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deleteById(@RequestParam(value = "orderIds") List<Integer> orderIds) throws Exception{
        SqlSession sqlSession = MybatisSessionFactory.getSession();
        //使用mapper批量删除
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andIdIn(orderIds);
        try{
            orderMapper.deleteByExample(orderExample);
            return "删除成功";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
}