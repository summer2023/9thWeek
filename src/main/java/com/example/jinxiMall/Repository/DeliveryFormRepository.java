package com.example.jinxiMall.Repository;

import com.example.jinxiMall.entity.DeliveryForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface DeliveryFormRepository extends JpaRepository<DeliveryForm, Long> {
    //创建订单
    @Transactional
    DeliveryForm save(DeliveryForm deliveryForm);

    //根据物流Id和订单id查询订单
    DeliveryForm findByIdAndOrderId(Long id, Long orderId);

    //根据订单Id查找物流
    DeliveryForm findDeliveryFormById(Long id);

    //修改物流发货状态
    @Modifying
    @Transactional
    @Query("update DeliveryForm u set u.status = 'shipping', u.outboundTime = ?3 where u.id = ?1 and u.orderId = ?2")
    int updateStatusWithShipping(Long id, Long orderId, String sendTime);

    @Modifying
    @Transactional
    @Query("update DeliveryForm u set u.status = 'signed', u.signedTime = ?3 where u.id = ?1 and u.orderId = ?2")
    int updateStatusWithSigned(Long id, Long orderId, String signedTime);
}
