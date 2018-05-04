package com.boot.cloudadmin.common.enumobj;

import java.util.ArrayList;
import java.util.List;

/**
 * 附件类型enum
 */
public enum AttachTypeEnum {
    //关联类型 1、新闻资讯 2、项目 3、资讯富文本 4、项目富文本'
    /**
     * 新闻资讯
     */
    NEWS(1,"新闻资讯图片"),
    /**
     * 项目
     */
    PROJECT(2,"项目图片"),
    NEWS_EDIT(3,"资讯富文本"),
    PROJECT_EDIT(4,"资讯富文本");

    private int value;

    private String valueInFact;

    AttachTypeEnum(int value,String valueInFact) {
        this.value = value;
        this.valueInFact = valueInFact;
    }

    /**
     * <b>概要：</b>
     * 	根据属性值匹配属性
     * @param value 需要匹配的属性值
     * @return
     */
    public static AttachTypeEnum convertByValue(Integer value){
        for (AttachTypeEnum attachTypeEnum:AttachTypeEnum.values()) {
            if (attachTypeEnum.getValue() == value) {
                return attachTypeEnum;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getValueInFact() {
        return valueInFact;
    }

    public void setValueInFact(String valueInFact) {
        this.valueInFact = valueInFact;
    }
}
