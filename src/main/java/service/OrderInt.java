package service;

import model.Order;
import model.Orderitem;
import model.Product;
import model.User;

import java.util.List;

public interface OrderInt {
    List<Order> getOrders(User user);

    List<Order> listAll();

    Order getOder(int id);
    void deleteOrder(int oid);

    void update(Order order);

    void insert(Order order);
}
