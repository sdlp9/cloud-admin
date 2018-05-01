package com.boot.cloudadmin.movie.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.boot.cloudadmin.movie.entity.NewsEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsDao extends BaseMapper<NewsEntity>{
}
