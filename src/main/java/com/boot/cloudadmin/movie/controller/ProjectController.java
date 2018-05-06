package com.boot.cloudadmin.movie.controller;

import com.boot.cloudadmin.common.annotation.SysLog;
import com.boot.cloudadmin.common.base.BaseController;
import com.boot.cloudadmin.common.base.PageUtils;
import com.boot.cloudadmin.common.base.R;
import com.boot.cloudadmin.common.enumobj.DataStatusEnum;
import com.boot.cloudadmin.common.enumobj.ExamineStatusEnum;
import com.boot.cloudadmin.common.validator.ValidatorUtils;
import com.boot.cloudadmin.common.xss.XssHttpServletRequestWrapper;
import com.boot.cloudadmin.movie.entity.NewsEntity;
import com.boot.cloudadmin.movie.entity.ProjectEntity;
import com.boot.cloudadmin.movie.form.ApprovalForm;
import com.boot.cloudadmin.movie.service.IProjectService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


/**
 * 项目表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-05-05 15:11:34
 */
@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private IProjectService projectService;

    @RequestMapping("/projectList")
    public ModelAndView projectList(){
        logger.info("项目列表跳转 --->");
        return new ModelAndView("views/project/project-list");
    }

    @RequestMapping("/approvalList")
    public ModelAndView approvalList(){
        logger.info("项目审核列表跳转 --->");
        return new ModelAndView("views/project/project-approval");
    }

    /**
     * 获取列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public R proList(HttpServletRequest request){
        Map<String,Object> params = this.getAllParams(request);
        logger.info("获取项目列表---> params: " + params.toString());
        PageUtils page = projectService.queryPage(params);
        return R.ok().put("count",page.getTotalCount()).put("data",page.getList());
    }

    /**
     * 获取审核列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/examinelist")
//    @RequiresPermissions("sys:project:save")
    public R examinelist(HttpServletRequest request){
        Map<String,Object> params = this.getAllParams(request);
        params.put("isExmain","yes");
        logger.info("获取项目审核列表---> params: " + params.toString());
        PageUtils page = projectService.queryPage(params);
        return R.ok().put("count",page.getTotalCount()).put("data",page.getList());
    }

    @SysLog("保存项目")
    @RequestMapping("/save")
//    @RequiresPermissions("sys:project:save")
    @ResponseBody
    public R save(HttpServletRequest request){
        HttpServletRequest orgRequest = XssHttpServletRequestWrapper.getOrgRequest(request);
        String projectName = orgRequest.getParameter("projectName");
        String projectCost = orgRequest.getParameter("projectCost");
        String projectDes = orgRequest.getParameter("projectDes");
        String projectHeader = orgRequest.getParameter("projectHeader");
        String financingMoney = orgRequest.getParameter("financingMoney");
        String startTime = orgRequest.getParameter("startTime");
        String endTime = orgRequest.getParameter("endTime");
        String projectLabel = orgRequest.getParameter("projectLabel");
        String projectCoverCharge = orgRequest.getParameter("projectCoverCharge");
        String projectType = orgRequest.getParameter("projectType");
        String projectStatus = orgRequest.getParameter("projectStatus");
        String indexFlag = orgRequest.getParameter("indexFlag");
        String projectVideo = orgRequest.getParameter("projectVideo");
        String attachId = orgRequest.getParameter("attachId");

        logger.info("保存项目信息：params：projectName ---> " + projectName + ", projectDes: " + projectDes
                + ", projectCost: " + projectCost
                + ", financingMoney: " + financingMoney
                + ", startTime: " + startTime
                + ", endTime: " + endTime
                + ", projectLabel: " + projectLabel
                + ", projectCoverCharge: " + projectCoverCharge
                + ", projectType: " + projectType
                + ", projectStatus: " + projectStatus
                + ", indexFlag: " + indexFlag
                + ", projectVideo: " + projectVideo
                + ", attachId: " + attachId
                + ", projectHeader: " + projectHeader);
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName(projectName);
        projectEntity.setProjectDes(projectDes);
        projectEntity.setProjectCost(new BigDecimal(projectCost));
        projectEntity.setFinancingMoney(new BigDecimal(financingMoney));
        projectEntity.setStartTime(startTime);
        projectEntity.setEndTime(endTime);
        projectEntity.setProjectLabel(projectLabel);
        projectEntity.setProjectCoverCharge(new BigDecimal(projectCoverCharge));
        projectEntity.setProjectType(Integer.parseInt(projectType));
        projectEntity.setProjectStatus(Integer.parseInt(projectStatus));
        projectEntity.setIndexFlag(Integer.parseInt(indexFlag));
        projectEntity.setProjectVideo(projectVideo);
        projectEntity.setAttachId(attachId);
        projectEntity.setProjectHeader(projectHeader);
        projectEntity.setExamineStatus(ExamineStatusEnum.CREATE.getValue());
        projectEntity.setPublishStatus(DataStatusEnum.OTHERS.getValue());
        projectService.save(projectEntity);
        return R.ok();
    }

    /**
     * 获取项目信息
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/info/{id}")
//    @RequiresPermissions("sys:news:info")
    public R info(@PathVariable("id") String id){
        logger.info("获取用户信息：params：id ---> " + id);
        ProjectEntity project = projectService.selectById(Long.valueOf(id));
        return R.ok().put("project", project);
    }

    /**
     * 更新项目信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
//    @RequiresPermissions("sys:news:update")
    public R update(HttpServletRequest request){
        HttpServletRequest orgRequest = XssHttpServletRequestWrapper.getOrgRequest(request);
        String id = orgRequest.getParameter("id");
        String projectName = orgRequest.getParameter("projectName");
        String projectCost = orgRequest.getParameter("projectCost");
        String projectDes = orgRequest.getParameter("projectDes");
        String projectHeader = orgRequest.getParameter("projectHeader");
        String financingMoney = orgRequest.getParameter("financingMoney");
        String startTime = orgRequest.getParameter("startTime");
        String endTime = orgRequest.getParameter("endTime");
        String projectLabel = orgRequest.getParameter("projectLabel");
        String projectCoverCharge = orgRequest.getParameter("projectCoverCharge");
        String projectType = orgRequest.getParameter("projectType");
        String projectStatus = orgRequest.getParameter("projectStatus");
        String indexFlag = orgRequest.getParameter("indexFlag");
        String projectVideo = orgRequest.getParameter("projectVideo");
        String attachId = orgRequest.getParameter("attachId");

        logger.info("修改项目信息：params：projectName ---> " + projectName + ", projectDes: " + projectDes
                + ", projectCost: " + projectCost
                + ", financingMoney: " + financingMoney
                + ", startTime: " + startTime
                + ", endTime: " + endTime
                + ", projectLabel: " + projectLabel
                + ", projectCoverCharge: " + projectCoverCharge
                + ", projectType: " + projectType
                + ", projectStatus: " + projectStatus
                + ", indexFlag: " + indexFlag
                + ", projectVideo: " + projectVideo
                + ", attachId: " + attachId
                + ", projectHeader: " + projectHeader);
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(Long.valueOf(id));
        projectEntity.setProjectName(projectName);
        projectEntity.setProjectDes(projectDes);
        projectEntity.setProjectCost(new BigDecimal(projectCost));
        projectEntity.setFinancingMoney(new BigDecimal(financingMoney));
        projectEntity.setStartTime(startTime);
        projectEntity.setEndTime(endTime);
        projectEntity.setProjectLabel(projectLabel);
        projectEntity.setProjectCoverCharge(new BigDecimal(projectCoverCharge));
        projectEntity.setProjectType(Integer.parseInt(projectType));
        projectEntity.setProjectStatus(Integer.parseInt(projectStatus));
        projectEntity.setIndexFlag(Integer.parseInt(indexFlag));
        projectEntity.setProjectVideo(projectVideo);
        projectEntity.setAttachId(attachId);
        projectEntity.setProjectHeader(projectHeader);
        projectService.update(projectEntity);
        return R.ok();
    }

    /**
     * 删除项目
     * @param projectIds
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteByIds")
//    @RequiresPermissions("sys:user:delete")
    public R deleteByIds(@RequestBody Long[] projectIds){
        logger.info("批量删除项目信息：params：projectIds ---> " + projectIds.toString());
        projectService.deleteBatch(projectIds);
        return R.ok();
    }

    /**
     * 提审
     * @param approvalForm
     * @return
     */
    @ResponseBody
    @RequestMapping("/approval")
    //@RequiresPermissions("project:project:approval")
    public R approval(@RequestBody ApprovalForm approvalForm){

        R r = ValidatorUtils.validateEntity(approvalForm);
        if(!r.get("code").toString().equals("0")){
            return r;
        }
        ProjectEntity projectEntity = projectService.selectById(approvalForm.getId());
        if(projectEntity != null){
            Integer status = projectEntity.getExamineStatus();
            if(status != ExamineStatusEnum.CREATE.getValue() && status != ExamineStatusEnum.GOBACK.getValue()){
                return R.error("项目不能重复提审！");
            }
        }
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",approvalForm.getId());
        params.put("examineStatus",approvalForm.getExamineStatus());
        projectService.updateByMap(params);
        return R.ok();
    }

    /**
     * 发布
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/publish")
    //@RequiresPermissions("project:project:publish")
    public R publishPro(@RequestBody Long id){
        ProjectEntity projectEntity = projectService.selectById(id);
        Integer status = projectEntity.getExamineStatus();
        Integer publishStatus = projectEntity.getPublishStatus();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",id);
        if(status != ExamineStatusEnum.EXAMINEOK.getValue()){
            return R.error("项目未经过审核，不允许发布/下架！");
        }else{
            if(publishStatus == DataStatusEnum.OK.getValue()){
                params.put("publishStatus",DataStatusEnum.OTHERS.getValue());
            }else{
                params.put("publishStatus",DataStatusEnum.OK.getValue());
            }
            projectService.updateByMap(params);
            return R.ok();
        }
    }

    /**
     * 项目审核
     * @param approvalForm
     * @return
     */
    @ResponseBody
    @RequestMapping("/examine")
    //@RequiresPermissions("project:project:approval")
    public R examineProject(@RequestBody ApprovalForm approvalForm){
        R r = ValidatorUtils.validateEntity(approvalForm);
        if(!r.get("code").toString().equals("0")){
            return r;
        }
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",approvalForm.getId());
        params.put("examineStatus",approvalForm.getExamineStatus());
        params.put("remark",approvalForm.getRemark());
        projectService.updateByMap(params);
        return R.ok();
    }
}
