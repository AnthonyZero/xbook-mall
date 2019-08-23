package com.xbook.order.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xbook.common.constant.SysConstant;
import com.xbook.dao.order.OrderItemMapper;
import com.xbook.dao.order.OrderMapper;
import com.xbook.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = SysConstant.XBOOK_MALL_ORDER_VERSION)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
}
