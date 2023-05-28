package com.atguigu.mxbmall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.mxbmall.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author erfenjiao
 * @email gyf2002cc@gmail.com
 * @date 2023-05-28 10:46:46
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

