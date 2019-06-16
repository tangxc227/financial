package com.tangxc.entity.enums;

/**
 * 订单类型
 */
public enum OrderType {

    APPLY("申购"),
    REDEEM("赎回");

    public String desc;

    OrderType(String desc) {
        this.desc = desc;
    }

}
