package com.alix.order.enums;

import lombok.Getter;

/**
 * @author Alix
 * @date 2019-03-09 22:21
 */
@Getter
public enum OrderStatusEnun {

    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"取消"),
    ;

    private Integer code;

    private String msg;

    OrderStatusEnun(Integer code, String msg){
        this.code = code;
        this.msg = msg;

    }
}

