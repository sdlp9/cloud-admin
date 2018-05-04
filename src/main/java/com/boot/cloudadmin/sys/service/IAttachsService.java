package com.boot.cloudadmin.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.boot.cloudadmin.sys.entity.AttachsEntity;

import java.util.Map;

/**
 * 附件记录表
 *
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-05-04 23:53:33
 */
public interface IAttachsService extends IService<AttachsEntity> {

    void updateRelId(Map<String,Object> params);
}

