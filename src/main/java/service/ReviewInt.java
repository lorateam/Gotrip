package service;

import model.Review;

import java.util.List;

public interface ReviewInt {

    void insert(Review review);

    List<Review> listReview(int product_id);
}
