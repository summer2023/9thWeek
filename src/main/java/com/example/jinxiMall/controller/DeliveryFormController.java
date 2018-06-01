package com.example.jinxiMall.controller;

import com.example.jinxiMall.Repository.DeliveryFormRepository;
import com.example.jinxiMall.Repository.InventoryRepository;
import com.example.jinxiMall.Repository.OrderRepository;
import com.example.jinxiMall.Repository.ProductSnapRepository;
import com.example.jinxiMall.entity.DeliveryForm;
import com.example.jinxiMall.entity.ProductSnap;
import com.example.jinxiMall.exceptions.ItemNotFoundException;
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
    private ProductSnapRepository productSnapRepository;
    @Autowired
    private InventoryRepository inventoryRepository;

    //根据订单id查找物流
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public DeliveryForm getLogisticsRecord(@PathVariable Long id) throws Exception {
        DeliveryForm deliveryForm = deliveryFormRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("logisticsRecord", id));
        return deliveryForm;
    }

    //修改物流状态
    @PutMapping("{id}/orders/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrderStatus(@PathVariable Long id, @PathVariable Long orderId, @RequestParam String logisticsStatus) throws Exception {
       DeliveryForm deliveryForm = deliveryFormRepository.findByIdAndOrderId(id, orderId);
        String nowDate = String.valueOf(new Date(System.currentTimeMillis()));
        if (deliveryForm == null) {
            throw new ItemNotFoundException("logisticsRecord", "logisticsId", id, "orderId", orderId);
        }
        final boolean isLogisticsAlreadyShippedOrSigned = deliveryForm.getLogisticsStatus().equals("shipping") || deliveryForm.getLogisticsStatus().equals("signed");
        if (logisticsStatus.equals("signed")) {
            updateOrderStatusAndInventories(orderId, nowDate);
        }
        updateLogisticsStatus(logisticsStatus, id, orderId, nowDate);
    }

    private void updateOrderStatusAndInventories(Long orderId, String nowDate) {
        orderRepository.updateOrderStatusToFinished(orderId, "finished", nowDate);
        updateInventoriesAfterSignedOff(orderId);
    }

    private void updateLogisticsStatus(String logisticsStatus, Long id, Long orderId, String nowDate) {
        if (logisticsStatus.equals("shipping")) {
            deliveryFormRepository.updateStatusWithShipping(id, orderId, nowDate);
        } else if (logisticsStatus.equals("signed")) {
            deliveryFormRepository.updateStatusWithSigned(id, orderId, nowDate);
        }

    }

    private String checkWhetherCanSignLogistics(DeliveryForm deliveryForm) {
        if (deliveryForm.getLogisticsStatus().equals("readyToShip")) {
            return "The logisticsRecord hasn't been shipped yet.";
        }
        if (deliveryForm.getLogisticsStatus().equals("signed")) {
            return "The logisticsRecord has already been signed off.";
        }
        return "success";
    }

    private void updateInventoriesAfterSignedOff(Long orderId) {
        List<ProductSnap> products = productSnapRepository.findProductSnapByOrderId(orderId);
        for (ProductSnap product : products) {
            inventoryRepository.updateCountById(product.getId(), -product.getPurchaseCount());
            inventoryRepository.updateLockedCount(product.getId(), -product.getPurchaseCount());
        }
    }

}
