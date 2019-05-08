package com.imooc.order.enums;

import lombok.Getter;

@Getter
public enum  PayStatus {
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功"),
    FAILED(2,"支付失败"),
    ;
    private Integer code;
    private String message;

    PayStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
