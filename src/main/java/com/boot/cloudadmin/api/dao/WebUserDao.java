package com.boot.cloudadmin.api.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.boot.cloudadmin.api.entity.WebUserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WebUserDao extends BaseMapper<WebUserEntity> {
}
