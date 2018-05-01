package com.boot.cloudadmin.movie.service;

import com.baomidou.mybatisplus.service.IService;
import com.boot.cloudadmin.common.base.PageUtils;
import com.boot.cloudadmin.movie.entity.NewsEntity;

import java.util.Map;

public interface INewsService extends IService<NewsEntity> {
    /**
     * 获取分页列表
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存新闻
     */
    void save(NewsEntity newsEntity);

    /**
     * 修改新闻
     */
    void update(NewsEntity newsEntity);

    /**
     * 删除新闻
     */
    void deleteBatch(Long[] newsIds);
}
