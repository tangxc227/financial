package com.tangxc.manager.error;

/**
 * 错误种类
 */
public enum ErrorEnum {

    ID_NOT_NULL("F001", "编号不能为空", false),
    UNKNOWN("999", "未知异常", false);

    public String code;
    public String message;
    public boolean canRetry;

    ErrorEnum(String code, String message, boolean canRetry) {
        this.code = code;
        this.message = message;
        this.canRetry = canRetry;
    }

    public static ErrorEnum getByCode(String code) {
        for (ErrorEnum errorEnum : values()) {
            if (errorEnum.code.equals(code)) {
                return errorEnum;
            }
        }
        return null;
    }
}
