package com.boot.cloudadmin.common.enumobj;

/**
 * 附件类型enum
 */
public enum AttachTypeEnum {
    /**
     * 新闻资讯
     */
    NEWS(1),
    /**
     * 项目
     */
    PROJECT(2);

    private int value;

    AttachTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
