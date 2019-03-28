package com.alix.order.controller;

import com.alix.order.dto.CartDTO;
import com.alix.order.client.ProductClient;
import com.alix.order.dataobject.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author Alix
 * @date 2019-03-14 13:51
 */
@Slf4j
@RestController
public class ClientController {

    @Autowired
    private ProductClient productClient;


    @GetMapping("/getProductMsg")
    public String getProductMsg(){
        String respone = productClient.productMsg();
        return respone;
    }

    @GetMapping("/getProductList")
    public String getProductList(){
        List<ProductInfo> productInfoList = productClient.listForOrder(Arrays.asList("157875196366160022","157875227953464068"));
        log.info("respone={}",productInfoList);
        return "ok";
    }

    @GetMapping("/productDecreaseStock")
    public String productDecreaseStock(){
        productClient.decreaseStock(Arrays.asList(new CartDTO("164103465734242707",2)));
        return "ok";
    }

}
