package com.boot.cloudadmin.movie.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.boot.cloudadmin.common.base.PageUtils;
import com.boot.cloudadmin.common.base.Query;
import com.boot.cloudadmin.common.enumobj.DataStatusEnum;
import com.boot.cloudadmin.movie.dao.MemberDao;
import com.boot.cloudadmin.movie.entity.MemberEntity;
import com.boot.cloudadmin.movie.service.IMemberService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("memberService")
public class IMemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements IMemberService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        EntityWrapper<MemberEntity> wrapper = new EntityWrapper<MemberEntity>();
        if(params.containsKey("name")){
            wrapper.like("name",params.get("name").toString());
        }
        if(params.containsKey("isExmain")){
            wrapper.eq("examine_status", DataStatusEnum.OTHERS.getValue());
        }
        Page<MemberEntity> page = this.selectPage(
                new Query<MemberEntity>(params).getPage(),wrapper);
        return new PageUtils(page);
    }
}
