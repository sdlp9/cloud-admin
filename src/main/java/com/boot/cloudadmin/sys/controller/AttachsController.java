package com.boot.cloudadmin.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.boot.cloudadmin.common.base.R;
import com.boot.cloudadmin.sys.entity.AttachsEntity;
import com.boot.cloudadmin.sys.form.AttachsForm;
import com.boot.cloudadmin.sys.service.IAttachsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 附件记录表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-05-04 23:53:33
 */
@RestController
@RequestMapping("/attachs")
public class AttachsController {

    private static final Logger logger = LoggerFactory.getLogger(AttachsController.class);

    @Autowired
    private IAttachsService attachsService;

    /**
     * 删除附件
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public R deleteById(@RequestBody long id){
        logger.info("附件删除 ---> id:" + id);
        attachsService.deleteById(id);
        return R.ok();
    }

    /**
     * 获取关联附件
     * @param form
     * @return
     */
    @RequestMapping("getAttachsList")
    public R getAttachsList(@RequestBody AttachsForm form){
        logger.info("查询记录关联的附件 ---> params： " + form.toString());
        List<AttachsEntity> list = attachsService.selectList(new EntityWrapper<AttachsEntity>()
                .eq("rel_id",form.getRelId()).eq("attach_type",form.getAttachType())
        );

        return R.ok().put("list",list);
    }
    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("mark:tbattachs:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
//        LayuiPage page = tBAttachsService.queryPage(params);
//
//        ExcelUtils.exportExcelToTarget(response, "附件记录表", page.getData(), TBAttachsBean.class);
    }

}
