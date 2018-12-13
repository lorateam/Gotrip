package service;

import mapper.*;
import model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService implements ReviewInt {
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
    public void insert(Review review){
        reviewMapper.insert(review);
    }
    @Override
    public List<Review> listReview(int product_id){
        return reviewMapper.listReviews(product_id);
    }
}
