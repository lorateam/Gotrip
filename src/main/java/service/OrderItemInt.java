package service;

import model.Order;
import model.Orderitem;
import model.Product;
import model.User;

import java.util.*;

public interface OrderItemInt {
    Map<String, Object> buyOne(User user, Orderitem orderitem);

    Order newOrder(Orderitem orderitem);

    List<Orderitem> getOrderItem(int order_id);
}
