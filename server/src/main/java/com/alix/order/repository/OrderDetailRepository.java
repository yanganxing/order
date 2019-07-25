package com.alix.order.repository;

import com.alix.order.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alix
 * @date 2019-03-07 0:22
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String > {
}
