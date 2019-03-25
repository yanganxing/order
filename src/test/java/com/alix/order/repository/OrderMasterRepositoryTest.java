package com.alix.order.repository;

import com.alix.order.OrderApplicationTests;
import com.alix.order.dataobject.OrderMaster;
import com.alix.order.enums.OrderStatusEnun;
import com.alix.order.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


/**
 * @author Alix
 * @date 2019-03-09 22:07
 */
@Component
public class OrderMasterRepositoryTest  extends OrderApplicationTests {

    @Autowired
    private  OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("11");
        orderMaster.setBuyerName("gufang");
        orderMaster.setBuyerPhone("123567");
        orderMaster.setBuyerAddress("天安门");
        orderMaster.setBuyerOpenid("110100");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        orderMaster.setOrderStatus(OrderStatusEnun.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        OrderMaster orderMasetResult = orderMasterRepository.save(orderMaster);
        Assert.assertTrue(orderMasetResult != null);
    }
}