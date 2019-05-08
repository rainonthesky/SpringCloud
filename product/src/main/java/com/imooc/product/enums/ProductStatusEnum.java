package com.imooc.product.enums;
import lombok.Getter;

/**
 * 商品上下架状态
 */
@Getter
public enum ProductStatusEnum {
    UP(1,"在架"),
    DOWN(2,"下架");
    private Integer code;
    private String message;
    ProductStatusEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }
}
