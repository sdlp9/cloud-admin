package com.boot.cloudadmin.movie.service;

import com.baomidou.mybatisplus.service.IService;
import com.boot.cloudadmin.common.base.PageUtils;
import com.boot.cloudadmin.movie.entity.MemberEntity;

import java.util.Map;

/**
 * 会员表
 *
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-05-06 18:43:09
 */
public interface IMemberService extends IService<MemberEntity> {
    /**
     * 获取分页列表
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);
}

