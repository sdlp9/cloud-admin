package com.boot.cloudadmin.movie.controller;

import com.boot.cloudadmin.common.annotation.SysLog;
import com.boot.cloudadmin.common.base.BaseController;
import com.boot.cloudadmin.common.base.PageUtils;
import com.boot.cloudadmin.common.base.R;
import com.boot.cloudadmin.common.validator.ValidatorUtils;
import com.boot.cloudadmin.common.validator.group.AddGroup;
import com.boot.cloudadmin.common.validator.group.UpdateGroup;
import com.boot.cloudadmin.common.xss.XssHttpServletRequestWrapper;
import com.boot.cloudadmin.movie.entity.NewsEntity;
import com.boot.cloudadmin.movie.service.INewsService;
import com.boot.cloudadmin.sys.entity.UserEntity;
import com.boot.cloudadmin.sys.service.IAttachsService;
import org.apache.commons.lang.StringUtils;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private INewsService iNewsService;

    @Autowired
    private IAttachsService attachsService;

    @RequestMapping("/newsList")
    public ModelAndView newsList(){
        logger.info("新闻资讯列表跳转 --->");
        return new ModelAndView("views/news/news-list");
    }

    /**
     * 获取列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequiresPermissions("sys:news:list")
    @RequestMapping("/list")
    public R newsList(HttpServletRequest request){
        Map<String,Object> params = this.getAllParams(request);
        logger.info("获取新闻列表---> params: " + params.toString());
        PageUtils page = iNewsService.queryPage(params);
        return R.ok().put("count",page.getTotalCount()).put("data",page.getList());
    }
    /**
     * 资讯信息
     */
    @ResponseBody
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:news:info")
    public R info(@PathVariable("id") String id){
        logger.info("获取用户信息：params：id ---> " + id);
        NewsEntity news = iNewsService.selectById(Long.valueOf(id));
        return R.ok().put("news", news);
    }

    /**
     * 保存新闻
     */
    @SysLog("保存资讯")
    @RequestMapping("/save")
    @RequiresPermissions("sys:news:save")
    @ResponseBody
    public R save(HttpServletRequest request){
        HttpServletRequest orgRequest = XssHttpServletRequestWrapper.getOrgRequest(request);
        String title = orgRequest.getParameter("title");
        String content = orgRequest.getParameter("content");
        String type = orgRequest.getParameter("type");
        String showFlag = orgRequest.getParameter("showFlag");
        String attachId = orgRequest.getParameter("attachId");
        /**id: vm.news.id,
         title: vm.news.title,
         type: vm.news.type,
         content: layedit.getContent(contentEdit),
         showFlag: vm.news.showFlag
         */
        logger.info("保存资讯信息：params：title ---> " + title + ", content: " + content + ", type: " + type + ", showFlag: " + showFlag);
        NewsEntity news = new NewsEntity();
        news.setContent(content);
        news.setShowFlag(Integer.parseInt(showFlag));
        news.setTitle(title);
        news.setType(Integer.parseInt(type));
        news.setAttachId(attachId);
        iNewsService.save(news);
        return R.ok();
    }

    /**
     * 更新资讯
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("sys:news:update")
    public R updateNews(HttpServletRequest request){
        HttpServletRequest orgRequest = XssHttpServletRequestWrapper.getOrgRequest(request);
        String id = orgRequest.getParameter("id");
        String title = orgRequest.getParameter("title");
        String content = orgRequest.getParameter("content");
        String type = orgRequest.getParameter("type");
        String showFlag = orgRequest.getParameter("showFlag");
        logger.info("修改资讯信息：params : id --->" + id + ", title ---> " + title + ", content--->" + content + ", type--->" + type + ", showFlag--->" + showFlag);
        NewsEntity news = new NewsEntity();
        news.setId(Long.valueOf(id));
        news.setContent(content);
        news.setShowFlag(Integer.parseInt(showFlag));
        news.setTitle(title);
        news.setType(Integer.parseInt(type));
        iNewsService.update(news);

        return R.ok();
    }

    /**
     * 批量删除
     * @param newsIds
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteByIds")
    @RequiresPermissions("sys:user:delete")
    public R deleteByIds(@RequestBody Long[] newsIds){
        logger.info("批量删除用户信息：params：news ---> " + newsIds.toString());
        iNewsService.deleteBatch(newsIds);
        return R.ok();
    }
}
