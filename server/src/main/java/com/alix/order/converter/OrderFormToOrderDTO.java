package com.alix.order.converter;

import com.alix.order.dataobject.OrderDetail;
import com.alix.order.dto.OrderDTO;
import com.alix.order.enums.ResultEnum;
import com.alix.order.exception.OrderException;
import com.alix.order.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alix
 * @date 2019-03-12 0:42
 */
@Slf4j
public class OrderFormToOrderDTO {

    public static OrderDTO OrderFormToOrderDTO(OrderForm orderForm){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        Gson gson = new Gson();
        try{
            orderDetailList = gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("【json转换】错误sting={}",orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailsList(orderDetailList);
        return orderDTO;
    }
}
