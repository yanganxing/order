package com.alix.order.exception;

import com.alix.order.enums.ResultEnum;

/**
 * @author Alix
 * @date 2019-03-12 0:34
 */
public class OrderException extends  RuntimeException {

     private Integer code;

     public OrderException(Integer code,String message){
        super(message);
        this.code = code;
     }

     public OrderException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
     }
}
