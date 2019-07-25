package com.alix.order.service;

import com.alix.order.dto.OrderDTO;

/**
 * @author Alix
 * @date 2019-03-09 22:44
 */
public interface OrderService {

   OrderDTO create(OrderDTO orderDTO);
}
