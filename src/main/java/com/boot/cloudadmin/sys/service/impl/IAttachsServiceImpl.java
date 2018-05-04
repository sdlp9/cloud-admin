package com.boot.cloudadmin.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.boot.cloudadmin.sys.dao.AttachsDao;
import com.boot.cloudadmin.sys.entity.AttachsEntity;
import com.boot.cloudadmin.sys.service.IAttachsService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("attachsService")
public class IAttachsServiceImpl extends ServiceImpl<AttachsDao, AttachsEntity> implements IAttachsService {


    @Override
    public void updateRelId(Map<String, Object> params) {
        baseMapper.updateRelId(params);
    }
}
