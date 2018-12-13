package service;

import mapper.OrderMapper;
import mapper.OrderitemMapper;
import mapper.ProductMapper;
import mapper.ProductimageMapper;
import model.Order;
import model.Orderitem;
import model.Product;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderItemService implements OrderItemInt {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductimageMapper productimageMapper;
    @Autowired
    OrderitemMapper orderitemMapper;
    @Autowired
    OrderMapper orderMapper;
    @Override
    public Map<String, Object> buyOne(User user, Orderitem orderitem){
        Product product = productMapper.selectByPrimaryKey(orderitem.getPid());
        product.setFirstProductImage(productimageMapper.selectOneByPid(product.getId()));
        orderitem.setProduct(product);
        orderitem.setUid(user.getId());
        Order order = newOrder(orderitem);
        orderitem.setOid(order.getId());
        orderitemMapper.insert(orderitem);
        orderMapper.insert(order);
        List<Orderitem> ois = new ArrayList<>();
        ois.add(orderitem);
        order.setOrderItems(ois);
        Map<String, Object> map =  new HashMap();
        map.put("order", order);
        map.put("ois", ois);
        return map;
    }
    @Override
    public Order newOrder(Orderitem orderitem) {
        Order order = new Order();
        Long random = new Date().getTime();
        order.setId(random.intValue());
        order.setTotalNumber(orderitem.getNumber());
        order.setTotal(orderitem.getProduct().getPromotePrice()*orderitem.getNumber());
        order.setUid(orderitem.getUid());
        return order;
    }
    @Override
    public List<Orderitem> getOrderItem(int order_id){
        List<Orderitem> orderitems = orderitemMapper.selectByOrder(order_id);
        for(Orderitem item: orderitems){
            item.setProduct(productMapper.selectByPrimaryKey(item.getPid()));
        }
        return orderitems;
    }
}
