package com.imooc.product.dto;

import lombok.Data;

@Data
public class CartDto {
    /**
     * 商品的ID
     *
     */
    private String productId;
    /**
     * 商品的数量
     */
    private Integer productQuantity;

    public CartDto() {
    }
    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
