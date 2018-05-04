package com.boot.cloudadmin.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.boot.cloudadmin.sys.entity.AttachsEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 附件记录表
 *
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-05-04 23:53:33
 */
@Mapper
public interface AttachsDao extends BaseMapper<AttachsEntity> {
    void updateRelId(Map<String,Object> params);
}
