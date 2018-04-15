package com.boot.cloudadmin.common.enumobj;

/**
 * 数据状态
 */
public enum DataStatusEnum {

    /**正常**/
    OK(1),
    /**
     * 已删除
     */
    DELETE(2);

    private int value;

    DataStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
