package com.boot.cloudadmin.movie.controller;

import com.boot.cloudadmin.common.base.BaseController;
import com.boot.cloudadmin.common.base.PageUtils;
import com.boot.cloudadmin.common.base.R;
import com.boot.cloudadmin.movie.entity.MemberEntity;
import com.boot.cloudadmin.movie.service.IMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * 会员表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-05-06 18:43:09
 */
@Controller
@RequestMapping("/member")
public class MemberController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private IMemberService memberService;

    @RequestMapping("/memberList")
    public ModelAndView projectList(){
        logger.info("项目列表跳转 --->");
        return new ModelAndView("views/member/member-list");
    }

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
//    @RequiresPermissions("mark:tbmember:list")
    public R list(HttpServletRequest request){
        Map<String,Object> params = this.getAllParams(request);
        logger.info("获取会员列表---> params: " + params.toString());
        PageUtils page = memberService.queryPage(params);
        return R.ok().put("count",page.getTotalCount()).put("data",page.getList());
    }


    /**
     * 信息
     */
    @ResponseBody
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("mark:tbmember:info")
    public R info(@PathVariable("id") Long id){

        MemberEntity tBMember = memberService.selectById(id);

        return R.ok().put("member", tBMember);
    }

}
