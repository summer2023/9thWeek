package com.example.jinxiMall.Repository;

import com.example.jinxiMall.entity.DeliveryForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface DeliveryFormRepository extends JpaRepository<DeliveryForm, Long> {
    //创建订单
    DeliveryForm save(DeliveryForm logisticsRecord);

    //根据物流Id和订单id查询订单
    DeliveryForm findLogisticsRecordByIdAndOrderId(Long id, Long orderId);

    //修改物流发货状态
    @Modifying
    @Query("update LogisticsRecord u set u.logisticsStatus = 'shipping', u.outboundTime = ?3 where u.id = ?1 and u.orderId = ?2")
    int updateLogisticsStatusWithShipping(Long id, Long orderId, String sendTime);

    @Modifying
    @Query("update LogisticsRecord u set u.logisticsStatus = 'signed', u.signedTime = ?3 where u.id = ?1 and u.orderId = ?2")
    int updateLogisticsStatusWithSigned(Long id, Long orderId, String signedTime);
}
