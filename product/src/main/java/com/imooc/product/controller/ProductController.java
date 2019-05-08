package com.imooc.product.controller;

import com.imooc.product.dto.CartDto;
import com.imooc.product.entity.ProductCategory;
import com.imooc.product.entity.ProductInfo;
import com.imooc.product.service.CategoryService;
import com.imooc.product.service.ProductService;
import com.imooc.product.utils.ResultVoUtil;
import com.imooc.product.vo.ProductInfoVo;
import com.imooc.product.vo.ProductVo;
import com.imooc.product.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    /**
     * 1.查询所有在架商品
     * 2.获取类目type列表
     * 3.查询类目
     * 4.构造数据
     */
    @GetMapping("/list")
    public ResultVo<ProductVo> list(){
        List<ProductInfo>productInfoList=productService.finUpAll();
        List<Integer>categoryTypeList=productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
       List<ProductCategory>categoryList= categoryService.findByCategoryTypeIn(categoryTypeList);
        //构造数据
        List<ProductVo>productVoList=new ArrayList<>();
        for(ProductCategory productCategory:categoryList){
            ProductVo productVo=new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());
           List<ProductInfoVo> productInfoVoList=new ArrayList<>();
           for(ProductInfo productInfo:productInfoList){
               if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
               ProductInfoVo productInfoVo=new ProductInfoVo();
               BeanUtils.copyProperties(productInfo,productInfoVo);
               productInfoVoList.add(productInfoVo);
               }
           }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }
        return ResultVoUtil.success(productVoList);

    }

    /**
     * 获取商品列表（订单服务用的）
     */
    @PostMapping(value = "/listForOrder")
    public List<ProductInfo>listForOrder(@RequestBody  List<String> productIdList){
    return productService.findList(productIdList);
    }
    @PostMapping(value = "/decreaseStock")
    public void decreaseStock(@RequestBody List<CartDto>cartDtoList){

        productService.decreaseStock(cartDtoList);
    }

}















