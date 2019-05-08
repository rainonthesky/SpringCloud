package com.imooc.order.service.impl;

import com.imooc.order.client.ProductClient;
import com.imooc.order.dto.OrderDto;
import com.imooc.order.entity.OrderDetail;
import com.imooc.order.entity.OrderMaster;
import com.imooc.order.enums.OrderStatus;
import com.imooc.order.enums.PayStatus;
import com.imooc.order.repository.OrderDetailRepository;
import com.imooc.order.repository.OrderMasterRepository;
import com.imooc.order.service.OrderService;
import com.imooc.order.utils.KeyUtil;
import com.imooc.product.dto.CartDto;
import com.imooc.product.entity.ProductInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private ProductClient productClient;
    @Override
    public OrderDto create(OrderDto orderDto) {
        String orderId=KeyUtil.genUniquekey();
        //查询商品（调用商品服务）
        List<String>productIdList=orderDto.getOrderDetailList().stream()
                                            .map(OrderDetail::getProductId)
                                            .collect(Collectors.toList());
        List<ProductInfo>productInfoList=productClient.listForOrder(productIdList);
        //计算总价
        BigDecimal orderAmount=new BigDecimal(BigInteger.ZERO);
        for(OrderDetail orderDetail:orderDto.getOrderDetailList()){
            for(ProductInfo productInfo:productInfoList){
                if(orderDetail.getProductId().equals(productInfo.getProductId())){
                    orderAmount=productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmount);
                    BeanUtils.copyProperties(productInfo,orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniquekey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }
        //扣库存
        List<CartDto>cartDtoList=orderDto.getOrderDetailList().stream()
                .map(e->new CartDto(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(cartDtoList);
        //订单入库
        OrderMaster orderMaster=new OrderMaster();
        orderDto.setOrderId(orderId);
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatus.NEW.getCode());
        orderMaster.setPayStatus(PayStatus.WAIT.getCode());
        return orderDto;
    }
}
