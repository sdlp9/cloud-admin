package com.boot.cloudadmin.movie.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.boot.cloudadmin.movie.entity.ProjectEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 项目表
 * 
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-05-05 15:11:34
 */
@Mapper
public interface ProjectDao extends BaseMapper<ProjectEntity> {

    /**
     * 更新项目
     * @param params
     */
    void updateByMap(Map<String, Object> params);
}
