package com.alix.order.service.impl;

import com.alix.order.client.ProductClient;
import com.alix.order.dataobject.OrderDetail;
import com.alix.order.dataobject.OrderMaster;
import com.alix.order.dataobject.ProductInfo;
import com.alix.order.dto.CartDTO;
import com.alix.order.dto.OrderDTO;
import com.alix.order.enums.OrderStatusEnun;
import com.alix.order.enums.PayStatusEnum;
import com.alix.order.repository.OrderDetailRepository;
import com.alix.order.repository.OrderMasterRepository;
import com.alix.order.service.OrderService;
import com.alix.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private ProductClient productClient;
    /*
     * 2、查询商品信息（调用商品服务）
     * 3、计算总价
     * 4、扣库存（调用商品服务）
     * 5、订单入库
     * */
    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId= KeyUtil.getUniqueKey();
        //TODO 查询商品信息（调用商品服务）
        List<String> productIdList = orderDTO.getOrderDetailsList().stream().map(OrderDetail::getDetailId).
                collect(Collectors.toList());
        List<ProductInfo> productInfoList = productClient.listForOrder(productIdList);
        //TODO  计算总价
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailsList()){
            for (ProductInfo productInfo : productInfoList){
                if(productInfo.getProductId().equals(orderDetail.getProductId())){
                    orderAmout = productInfo.getProductPrice().
                            multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmout);
                    BeanUtils.copyProperties(productInfo,orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.getUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
            orderDetail.getProductQuantity();
        }
        //TODO 扣库存（调用商品服务）
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailsList().stream().
                map(e -> new CartDTO(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
        productClient.decreaseStock(cartDTOList);
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatusEnun.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
