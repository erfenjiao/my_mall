package com.atguigu.mxbmall.order.dao;

import com.atguigu.mxbmall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author erfenjiao
 * @email gyf2002cc@gmail.com
 * @date 2023-05-28 11:00:23
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
