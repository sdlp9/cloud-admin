package com.boot.cloudadmin.movie.controller;

import com.boot.cloudadmin.common.annotation.SysLog;
import com.boot.cloudadmin.common.base.BaseController;
import com.boot.cloudadmin.common.base.PageUtils;
import com.boot.cloudadmin.common.base.R;
import com.boot.cloudadmin.common.validator.ValidatorUtils;
import com.boot.cloudadmin.common.validator.group.AddGroup;
import com.boot.cloudadmin.common.validator.group.UpdateGroup;
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
    public R save(@RequestBody NewsEntity news){
        logger.info("保存资讯信息：params：user ---> " + news.toString());
        R r = ValidatorUtils.validateEntity(news, AddGroup.class);
        if(!r.get("code").toString().equals("0")){
            return r;
        }
        iNewsService.save(news);
        return R.ok();
    }

    /**
     * 更新资讯
     * @param news
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("sys:news:update")
    public R updateNews(@RequestBody NewsEntity news){
        logger.info("更新资讯信息：params：news ---> " + news.toString());
        R r = ValidatorUtils.validateEntity(news, UpdateGroup.class);
        if(!r.get("code").toString().equals("0")){
            return r;
        }
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
