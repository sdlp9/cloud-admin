package com.boot.cloudadmin.movie.service;

import com.baomidou.mybatisplus.service.IService;
import com.boot.cloudadmin.common.base.PageUtils;
import com.boot.cloudadmin.movie.entity.ProjectEntity;
import com.boot.cloudadmin.movie.form.ApprovalForm;

import java.util.Map;

/**
 * 项目表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-05-05 15:11:34
 */
public interface IProjectService extends IService<ProjectEntity> {

    /**
     * 获取分页列表
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存信息
     * @param entity
     */
    void save(ProjectEntity entity);

    /**
     * 更新项目
     * @param entity
     */
    void update(ProjectEntity entity);

    /**
     * 删除项目
     * @param projectIds
     */
    void deleteBatch(Long[] projectIds);

    /**
     * 更新项目
     * @param params
     */
    void updateByMap(Map<String, Object> params);
}

