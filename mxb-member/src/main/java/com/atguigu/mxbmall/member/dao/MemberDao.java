package com.atguigu.mxbmall.member.dao;

import com.atguigu.mxbmall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author erfenjiao
 * @email gyf2002cc@gmail.com
 * @date 2023-05-28 10:46:46
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
