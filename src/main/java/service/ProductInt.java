package service;

import model.Product;
import model.Productimage;
import model.Property;
import model.Propertyvalue;

import java.util.List;

public interface ProductInt {
    Product getProduct(Integer id);

    void update(Product product);

    void insert(Product product);

    void delete(int id);

    List<Product> listProducts(int cid);

    List<Product> listProducts(String keyword);

    List<Propertyvalue> listProprotyValue(int product_id);

    List<Property> listProproty(int cid);

    Property getProperty(int id);

    void deleteProperty(int id);

    List<Productimage> listProductImage(int product_id);
}
