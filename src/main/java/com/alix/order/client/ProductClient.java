package com.alix.order.client;

import com.alix.order.dataobject.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author Alix
 * @date 2019-03-15 1:04
 */
@FeignClient(name = "product")
public interface ProductClient {

    @GetMapping("/getMsg")
    String productMsg();


    @PostMapping("/product/listForOrder")
    List<ProductInfo> listForOrder(List<String> productIdList);
}
