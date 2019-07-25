package com.alix.order.enums;

import lombok.Getter;

/**
 * @author Alix
 * @date 2019-03-12 0:37
 */
@Getter
public enum ResultEnum {

    PARAM_ERROR(1,"参数错误"),

    CART_EMPTY(2,"购物车为空")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }

}
