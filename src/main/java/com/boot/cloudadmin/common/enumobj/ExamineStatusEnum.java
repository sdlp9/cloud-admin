package com.boot.cloudadmin.common.enumobj;

/**
 * 附件类型enum
 */
public enum ExamineStatusEnum {
    //审核状态 1、新建 2、已提交未审核 3 驳回 4 审核通过
    CREATE(1,"未提审"),
    SUBMIT(2,"已提交未审核"),
    GOBACK(3,"驳回"),
    EXAMINEOK(4,"审核通过");

    private int value;

    private String valueInFact;

    ExamineStatusEnum(int value, String valueInFact) {
        this.value = value;
        this.valueInFact = valueInFact;
    }

    /**
     * <b>概要：</b>
     * 	根据属性值匹配属性
     * @param value 需要匹配的属性值
     * @return
     */
    public static ExamineStatusEnum convertByValue(Integer value){
        for (ExamineStatusEnum attachTypeEnum: ExamineStatusEnum.values()) {
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
