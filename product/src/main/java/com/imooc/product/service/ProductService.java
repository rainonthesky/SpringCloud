package com.imooc.product.service;

import com.imooc.product.dto.CartDto;
import com.imooc.product.entity.ProductInfo;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有上线的商品
     * @return
     */
    List<ProductInfo>finUpAll();

    /**
     * 查询商品列表
     * @param productIdList
     * @return
     */
    List<ProductInfo>findList(List<String>productIdList);

    /**
     * 扣库存
     * @param cartDtoList
     */

    void decreaseStock(List<CartDto>cartDtoList);
}
