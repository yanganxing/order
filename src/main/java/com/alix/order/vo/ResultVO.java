package com.alix.order.vo;

import lombok.Data;

/**
 * @author Alix
 * @date 2019-03-14 13:22
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private  T data;



}
