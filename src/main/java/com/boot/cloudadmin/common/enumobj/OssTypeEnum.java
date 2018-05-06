package com.boot.cloudadmin.common.enumobj;

/**
 * 数据状态
 */
public enum OssTypeEnum {

    FILESURL(1,"文件访问地址"),
    IMAGESURL(2,"图片访问地址"),
    VIEDOURL(3,"视频访问地址");

    private int value;

    private String valueInFact;

    OssTypeEnum(int value, String valueInFact) {
        this.value = value;
        this.valueInFact = valueInFact;
    }

    /**
     * <b>概要：</b>
     * 	根据属性值匹配属性
     * @param value 需要匹配的属性值
     * @return
     */
    public static OssTypeEnum convertByValue(Integer value){
        for (OssTypeEnum dataEnum: OssTypeEnum.values()) {
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
