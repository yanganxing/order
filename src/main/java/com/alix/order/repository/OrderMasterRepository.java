package com.alix.order.repository;

import com.alix.order.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alix
 * @date 2019-03-07 0:20
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
}
