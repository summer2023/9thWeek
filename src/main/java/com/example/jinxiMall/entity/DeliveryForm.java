package com.example.jinxiMall.entity;

import javax.persistence.*;

public class DeliveryForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private String signedStatus;
    private String sendTime;
    private String signedTime;
    private String deliveryMan = "X师傅";

    @OneToOne(targetEntity = UserOrder.class)
    @JoinColumn(name = "orderId", insertable = false, updatable = false)
    private UserOrder userOrder;

    public DeliveryForm() {
    }

    public DeliveryForm(Long orderId, String signedStatus) {
        this.orderId = orderId;
        this.signedStatus = signedStatus;
        this.sendTime = "null";
        this.signedTime = "null";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getLogisticsStatus() {
        return signedStatus;
    }

    public void setLogisticsStatus(String logisticsStatus) {
        this.signedStatus = logisticsStatus;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getSignedTime() {
        return signedTime;
    }

    public void setSignedTime(String signedTime) {
        this.signedTime = signedTime;
    }

    public String getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(String deliveryMan) {
        this.deliveryMan = deliveryMan;
    }
}
