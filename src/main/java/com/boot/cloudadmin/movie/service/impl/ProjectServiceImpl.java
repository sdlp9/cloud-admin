package com.boot.cloudadmin.movie.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.boot.cloudadmin.common.base.PageUtils;
import com.boot.cloudadmin.common.base.Query;
import com.boot.cloudadmin.common.enumobj.DataStatusEnum;
import com.boot.cloudadmin.movie.dao.ProjectDao;
import com.boot.cloudadmin.movie.entity.NewsEntity;
import com.boot.cloudadmin.movie.entity.ProjectEntity;
import com.boot.cloudadmin.movie.service.IProjectService;
import com.boot.cloudadmin.sys.dao.AttachsDao;
import com.boot.cloudadmin.sys.entity.AttachsEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service("projectService")
public class ProjectServiceImpl extends ServiceImpl<ProjectDao, ProjectEntity> implements IProjectService {

    @Autowired
    private AttachsDao attachsDao;

    @Autowired
    private ProjectDao projectDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        EntityWrapper<ProjectEntity> wrapper = new EntityWrapper<ProjectEntity>();
        if(params.containsKey("projectName")){
            wrapper.like("project_name",params.get("projectName").toString());
        }
        if(params.containsKey("isExmain")){
            wrapper.eq("examine_status", DataStatusEnum.OTHERS.getValue());
        }
        Page<ProjectEntity> page = this.selectPage(
                new Query<ProjectEntity>(params).getPage(),wrapper);
        return new PageUtils(page);
    }

    @Override
    public void save(ProjectEntity entity) {
        this.baseMapper.insert(entity);
        updateAttachRel(entity);
    }

    @Override
    public void update(ProjectEntity entity) {
        this.baseMapper.updateById(entity);
        updateAttachRel(entity);
    }

    @Override
    public void deleteBatch(Long[] projectIds) {
        for (Long projectId : projectIds){
            baseMapper.deleteById(projectId);
            //删除相关附件
            attachsDao.delete(new EntityWrapper<AttachsEntity>()
                    .eq("rel_id",projectId)
            );
        }
    }

    @Override
    public void updateByMap(Map<String, Object> params) {
        projectDao.updateByMap(params);
    }

    /**
     * 更新附件的关联id
     * @param entity
     */
    public void updateAttachRel(ProjectEntity entity){
        if(StringUtils.isNotBlank(entity.getAttachId())){
            String [] ids = entity.getAttachId().split(",");
            for (int i = 0;i<ids.length;i++){
                Map<String,Object> params = new HashMap<String,Object>();
                params.put("relId",entity.getId());
                params.put("id",ids[i]);
                attachsDao.updateRelId(params);
            }
        }
    }
}
