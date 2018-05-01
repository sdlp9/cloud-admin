package com.boot.cloudadmin.movie.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.boot.cloudadmin.common.base.PageUtils;
import com.boot.cloudadmin.common.base.Query;
import com.boot.cloudadmin.movie.dao.NewsDao;
import com.boot.cloudadmin.movie.entity.NewsEntity;
import com.boot.cloudadmin.movie.service.INewsService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("newsService")
public class NewsServiceImpl extends ServiceImpl<NewsDao,NewsEntity> implements INewsService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        EntityWrapper<NewsEntity> wrapper = new EntityWrapper<NewsEntity>();
        if(params.containsKey("title")){
            wrapper.like("title",params.get("title").toString());
        }
        Page<NewsEntity> page = this.selectPage(
                new Query<NewsEntity>(params).getPage(),wrapper);
        return new PageUtils(page);
    }

    @Override
    public void save(NewsEntity newsEntity) {
        this.baseMapper.insert(newsEntity);
    }

    @Override
    public void update(NewsEntity newsEntity) {
        this.baseMapper.updateById(newsEntity);
    }

    @Override
    public void deleteBatch(Long[] newsIds) {
        this.deleteBatch(newsIds);
    }
}
