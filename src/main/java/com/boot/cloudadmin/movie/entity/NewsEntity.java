package com.boot.cloudadmin.movie.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

@Setter
@Getter
@ToString
@TableName("t_b_news")
public class NewsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;
    /**
     * 资讯标题
     */
    private String title;
    /**
     * 资讯类型
     */
    private Integer type;
    /**
     * 状态 1、可用 2、已删除
     */
    private Integer status;
    /**
     * 是否显示 1 显示 0 不显示
     */
    private Integer showFlag;
    /**
     * 资讯内容
     */
    private String content;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 更新时间
     */
    private Timestamp updateTime;
}
