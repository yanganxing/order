package com.alix.order.service.impl;

import com.alix.order.dataobject.OrderMaster;
import com.alix.order.dto.OrderDTO;
import com.alix.order.enums.OrderStatusEnun;
import com.alix.order.enums.PayStatusEnum;
import com.alix.order.repository.OrderDetailRepository;
import com.alix.order.repository.OrderMasterRepository;
import com.alix.order.service.OrderService;
import com.alix.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Alix
 * @date 2019-03-09 22:49
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;


    /*
     * 2、查询商品信息（调用商品服务）
     * 3、计算总价
     * 4、扣库存（调用商品服务）
     * 5、订单入库
     * */
    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        //TODO 查询商品信息（调用商品服务）
        //TODO  计算总价
        //TODO 扣库存（调用商品服务）

        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(KeyUtil.getUniqueKey());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatusEnun.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
