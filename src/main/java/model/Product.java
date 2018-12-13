package model;

import java.util.Date;
import java.util.List;

public class Product {
    private Integer id;

    private String name;

    private Integer saleCount;

    private Integer reviewCount;

    private String subTitle;

    private Float orignalPrice;

    private Float promotePrice;

    private Integer stock;

    private Integer cid;

    private Date createDate;

    private Category category;
    private Productimage firstProductImage;
    private List<Productimage> productImages;
    private List<Productimage> productSingleImages;
    private List<Productimage> productDetailImages;

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public List<Productimage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<Productimage> productImages) {
        this.productImages = productImages;
    }

    public List<Productimage> getProductSingleImages() {
        return productSingleImages;
    }

    public void setProductSingleImages(List<Productimage> productSingleImages) {
        this.productSingleImages = productSingleImages;
    }

    public List<Productimage> getProductDetailImages() {
        return productDetailImages;
    }

    public void setProductDetailImages(List<Productimage> productDetailImages) {
        this.productDetailImages = productDetailImages;
    }

    public Productimage getFirstProductImage() {
        return firstProductImage;
    }

    public void setFirstProductImage(Productimage firstProductImage) {
        this.firstProductImage = firstProductImage;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle == null ? null : subTitle.trim();
    }

    public Float getOrignalPrice() {
        return orignalPrice;
    }

    public void setOrignalPrice(Float orignalPrice) {
        this.orignalPrice = orignalPrice;
    }

    public Float getPromotePrice() {
        return promotePrice;
    }

    public void setPromotePrice(Float promotePrice) {
        this.promotePrice = promotePrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}