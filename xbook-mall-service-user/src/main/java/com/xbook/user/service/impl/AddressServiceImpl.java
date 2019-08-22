package com.xbook.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.xbook.common.constant.SysConstant;
import com.xbook.common.enums.CodeMsgEnum;
import com.xbook.dao.user.ShippingMapper;
import com.xbook.entity.user.Shipping;
import com.xbook.user.service.AddressService;
import com.xbook.user.service.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service(version = SysConstant.XBOOK_MALL_USER_VERSION)
public class AddressServiceImpl implements AddressService {

    @Autowired
    private ShippingMapper shippingMapper;

    @Override
    @Transactional
    public Map saveAddress(Shipping shipping, Integer userId) {
        shipping.setCreateTime(LocalDateTime.now());
        shipping.setUserId(userId);
        int shippingId = shippingMapper.insert(shipping);
        Map result = Maps.newHashMap();
        result.put("shippingId", shippingId);
        return result;
    }

    @Override
    @Transactional
    public void removeAddress(Integer shippingId, Integer userId) {
        if (shippingId == null || userId == null) {
            throw new UserException(CodeMsgEnum.PARAMETER_ERROR);
        }
        shippingMapper.delete(new LambdaQueryWrapper<Shipping>().eq(Shipping::getId, shippingId).eq(Shipping::getUserId, userId));
    }

    @Override
    @Transactional
    public void updateAddress(Shipping shipping) {
        shipping.setUpdateTime(LocalDateTime.now());
        shippingMapper.updateById(shipping);
    }

    @Override
    public Shipping getAddress(Integer shippingId, Integer userId) {
        if (shippingId == null || userId == null) {
            throw new UserException(CodeMsgEnum.PARAMETER_ERROR);
        }
        Shipping shipping = shippingMapper.selectOne(new LambdaQueryWrapper<Shipping>().eq(Shipping::getId, shippingId).eq(Shipping::getUserId, userId));
        if (shipping == null) {
            throw new UserException(CodeMsgEnum.ADDRESS_NOT_EXIST);
        }
        return shipping;
    }

    @Override
    public PageInfo pageAddress(Integer userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Shipping> list = shippingMapper.selectList(new LambdaQueryWrapper<Shipping>().eq(Shipping::getUserId, userId));
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
