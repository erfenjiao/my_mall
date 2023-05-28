package com.atguigu.mxbmall.coupon.dao;

import com.atguigu.mxbmall.coupon.entity.CouponHistoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券领取历史记录
 * 
 * @author erfenjiao
 * @email gyf2002cc@gmail.com
 * @date 2023-05-28 10:41:26
 */
@Mapper
public interface CouponHistoryDao extends BaseMapper<CouponHistoryEntity> {
	
}
