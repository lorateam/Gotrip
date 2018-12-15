package service;

import mapper.*;
import model.Order;
import model.Orderitem;
import model.Product;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements OrderInt {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductimageMapper productimageMapper;
    @Autowired
    OrderitemMapper orderitemMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    ReviewMapper reviewMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public List<Order> getOrders(User user){
        List<Order> orders = orderMapper.selectByUid(user.getId());
        for(Order o: orders ){
            o.setOrderItems(orderitemMapper.selectByOrder(o.getId()));
            for(Orderitem i : o.getOrderItems()){
                int pid = i.getPid();
                Product p = productMapper.selectByPrimaryKey(pid);
                p.setReviewCount(reviewMapper.countByPid(pid));
                p.setSaleCount(orderitemMapper.countByPid(pid));
                p.setFirstProductImage(productimageMapper.selectOneByPid(pid));
                i.setProduct(p);
            }
        }
        return orders;
    }
    @Override
    public List<Order> listAll(){
        List<Order> orders = orderMapper.selectAll();
        return getOrders(orders);
    }
    @Override
    public Order getOrder(int id){
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Order> selectOrder(String status, Integer min, Integer max){
        List<Order> orders = orderMapper.selectOrder(status, min, max);
        return getOrders(orders);
    }

    private List<Order> getOrders(List<Order> orders) {
        for (Order order: orders){
            order.setUser(userMapper.selectByPrimaryKey(order.getUid()));
            order.setTotalNumber(orderitemMapper.countNumber(order.getId()));
        }
        return orders;
    }

    @Override
    public void deleteOrder(int oid){
        orderMapper.deleteByPrimaryKey(oid);
    }
    @Override
    public void update(Order order){
        orderMapper.updateByPrimaryKey(order);
    }
    @Override
    public void insert(Order order){
        orderMapper.insert(order);
    }

    @Override
    public List<Order> outDateOrder() {
        List<Order> orders= orderMapper.outDateOrder();
        return getOrders(orders);
    }
}
