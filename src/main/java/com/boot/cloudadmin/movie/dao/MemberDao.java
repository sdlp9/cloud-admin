package com.boot.cloudadmin.movie.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.boot.cloudadmin.movie.entity.MemberEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员表
 * 
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-05-06 18:43:09
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
