package com.example.jinxiMall.controller;

import com.example.jinxiMall.Repository.DeliveryFormRepository;
import com.example.jinxiMall.Repository.InventoryRepository;
import com.example.jinxiMall.Repository.OrderRepository;
import com.example.jinxiMall.entity.DeliveryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/logisticsRecords")
public class DeliveryFormController {
    @Autowired
    private DeliveryFormRepository deliveryFormRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private InventoryRepository inventoryRepository;

    //根据订单id查找物流
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public DeliveryForm getLogisticsRecord(@PathVariable Long id){
        DeliveryForm deliveryForm = deliveryFormRepository.findDeliveryFormById(id);
        return deliveryForm;
    }

    //修改物流状态
    @PutMapping("{id}/orders/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrderStatus(@PathVariable Long id, @PathVariable Long orderId, @RequestParam String logisticsStatus) throws Exception {
        DeliveryForm deliveryForm = deliveryFormRepository.findByIdAndOrderId(id, orderId);
        String nowDate = String.valueOf(new Date(System.currentTimeMillis()));

        final boolean isDeliveryInfoAlreadyShippedOrSigned = deliveryForm.getLogisticsStatus().equals("shipping") || deliveryForm.getLogisticsStatus().equals("signed");
        updateOrderStatusAndInventories(orderId, nowDate);
        updateLogisticsStatus(logisticsStatus, id, orderId, nowDate);
    }

    private void updateOrderStatusAndInventories(Long orderId, String nowDate) {
        orderRepository.updateOrderStatusToFinished(orderId, "finished", nowDate);
    }

    private void updateLogisticsStatus(String logisticsStatus, Long id, Long orderId, String nowDate) {
        if (logisticsStatus.equals("shipping")) {
            deliveryFormRepository.updateStatusWithShipping(id, orderId, nowDate);
        } else if (logisticsStatus.equals("signed")) {
            deliveryFormRepository.updateStatusWithSigned(id, orderId, nowDate);
        }

    }


}
