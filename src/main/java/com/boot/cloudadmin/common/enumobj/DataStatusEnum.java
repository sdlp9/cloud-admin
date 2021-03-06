package com.boot.cloudadmin.common.enumobj;

/**
 * 数据状态
 */
public enum DataStatusEnum {

    //数据状态 1、正常 2、
    OK(1,"1状态"),
    OTHERS(2,"2状态");

    private int value;

    private String valueInFact;

    DataStatusEnum(int value, String valueInFact) {
        this.value = value;
        this.valueInFact = valueInFact;
    }

    /**
     * <b>概要：</b>
     * 	根据属性值匹配属性
     * @param value 需要匹配的属性值
     * @return
     */
    public static DataStatusEnum convertByValue(Integer value){
        for (DataStatusEnum dataEnum: DataStatusEnum.values()) {
            if (dataEnum.getValue() == value) {
                return dataEnum;
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
