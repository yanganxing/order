package com.alix.order.enums;

import lombok.Getter;

/**
 * @author Alix
 * @date 2019-03-09 22:29
 */
@Getter
public enum PayStatusEnum {

    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功"),
    CANCEL(2,"取消"),
    ;

    private Integer code;

    private String msg;

    PayStatusEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;

    }
}
