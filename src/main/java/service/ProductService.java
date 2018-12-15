package service;

import mapper.*;
import model.*;
import model.Propertyvalue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductInt {
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
    @Autowired
    PropertyMapper propertyMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    PropertyvalueMapper propertyvalueMapper;
    @Override
    public Product getProduct(Integer id){
        List<Productimage> productSingleImages = productimageMapper.list(id, "type_single");
        List<Productimage> productDetailImages = productimageMapper.list(id, "type_detail");
        Product p = productMapper.selectByPrimaryKey(id);
        p.setProductSingleImages(productSingleImages);
        p.setProductDetailImages(productDetailImages);
        p.setReviewCount(reviewMapper.countByPid(id));
        p.setSaleCount(orderitemMapper.countByPid(id));
        p.setFirstProductImage(productimageMapper.selectOneByPid(id));
        p.setReviewCount(reviewMapper.countByPid(p.getId()));
        p.setCategory(categoryMapper.selectByPrimaryKey(p.getCid()));
        return p;
    }
    @Override
    public void update(Product product){
        productMapper.updateByPrimaryKey(product);
    }
    @Override
    public void insert(Product product){
        productMapper.insert(product);
    }
    @Override
    public void delete(int id){
        productMapper.deleteByPrimaryKey(id);
    }
    @Override
    public List<Product> listProducts(int cid){
        List<Product> ps = productMapper.selectByCid(cid);
        return getProducts(ps);
    }
    @Override
    public List<Product> listProducts(String keyword){
        keyword = "%"+keyword+"%";
        List<Product> ps = productMapper.selectByName(keyword);
        return getProducts(ps);
    }

    private List<Product> getProducts(List<Product> ps) {
        for(Product p:ps){
            p.setFirstProductImage(productimageMapper.selectOneByPid(p.getId()));
            p.setReviewCount(reviewMapper.countByPid(p.getId()));

        }
        return ps;
    }

    @Override
    public List<Propertyvalue> listProprotyValue(int product_id){
        return propertyvalueMapper.list(product_id);
    }
    @Override
    public List<Property> listProproty(int cid){
        return propertyMapper.selectByCid(cid);
    }
    @Override
    public Property getProperty(int id){
        Property property = propertyMapper.selectByPrimaryKey(id);
        property.setCategory(categoryMapper.selectByPrimaryKey(property.getCid()));
        return property;
    }
    @Override
    public void deleteProperty(int id){
        propertyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateProperty(Property record){
        propertyMapper.updateByPrimaryKey(record);
    }
    @Override
    public List<Productimage> listProductImage(int product_id){
        return productimageMapper.selectByPid(product_id);
    }

    @Override
    public void newProperty(Property record){
        propertyMapper.insert(record);
    }

    @Override
    public void updatePropertyValue(int id, String value){
        propertyvalueMapper.updateValue(id, value);
    }
}
