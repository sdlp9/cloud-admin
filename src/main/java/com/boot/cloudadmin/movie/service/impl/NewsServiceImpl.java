package com.boot.cloudadmin.movie.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.boot.cloudadmin.common.base.PageUtils;
import com.boot.cloudadmin.common.base.Query;
import com.boot.cloudadmin.common.enumobj.AttachTypeEnum;
import com.boot.cloudadmin.movie.dao.NewsDao;
import com.boot.cloudadmin.movie.entity.NewsEntity;
import com.boot.cloudadmin.movie.service.INewsService;
import com.boot.cloudadmin.sys.dao.AttachsDao;
import com.boot.cloudadmin.sys.entity.AttachsEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Transactional
@Service("newsService")
public class NewsServiceImpl extends ServiceImpl<NewsDao,NewsEntity> implements INewsService{

    @Autowired
    private AttachsDao attachsDao;

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
        if(StringUtils.isNotBlank(newsEntity.getAttachId())){
            String [] ids = newsEntity.getAttachId().split(",");
            for (int i = 0;i<ids.length;i++){
                Map<String,Object> params = new HashMap<String,Object>();
                params.put("relId",newsEntity.getId());
                params.put("id",ids[i]);
                attachsDao.updateRelId(params);
            }
        }
    }

    @Override
    public void update(NewsEntity newsEntity) {
        this.baseMapper.updateById(newsEntity);
    }

    @Override
    public void deleteBatch(Long[] newsIds) {
        for (Long newsId : newsIds){
            baseMapper.deleteById(newsId);
            //删除相关附件
            attachsDao.delete(new EntityWrapper<AttachsEntity>()
                    .eq("rel_id",newsId)
            );
        }
    }
}
