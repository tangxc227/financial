package com.tangxc.entity.enums;

/**
 * 订单类型
 */
public enum OrderStatus {

    INIT("初始化"),
    PROCESS("处理中"),
    SUCCESS("成功"),
    FAIL("失败");

    public String desc;

    OrderStatus(String desc) {
        this.desc = desc;
    }

}
