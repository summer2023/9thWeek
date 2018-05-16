package com.example.jinxiMall.entity;

public class OrderForm {private Long productId;
    private Integer purchaseCount;

    public OrderForm(Long productId, Integer purchaseCount) {
        this.productId = productId;
        this.purchaseCount = purchaseCount;
    }

    public OrderForm() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(Integer purchaseCount) {
        this.purchaseCount = purchaseCount;
    }
}
