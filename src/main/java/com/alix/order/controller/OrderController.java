package com.alix.order.controller;

import com.alix.order.converter.OrderFormToOrderDTO;
import com.alix.order.dto.OrderDTO;
import com.alix.order.enums.ResultEnum;
import com.alix.order.exception.OrderException;
import com.alix.order.form.OrderForm;
import com.alix.order.service.OrderService;
import com.alix.order.utils.ResultVOUtil;
import com.alix.order.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alix
 * @date 2019-03-09 22:41
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /*
    * 1、参数校验
    * 2、查询商品信息（调用商品服务）
    * 3、计算总价
    * 4、扣库存（调用商品服务）
    * 5、订单入库
    * */
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确，orderForm = {}",orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        //TODO 对象转换
        OrderDTO orderDTO = OrderFormToOrderDTO.OrderFormToOrderDTO(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailsList())){
            log.error("【创建订单】购物车为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }
        OrderDTO result = orderService.create(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",result.getOrderId());
        return ResultVOUtil.scuess(map);
    }


}
