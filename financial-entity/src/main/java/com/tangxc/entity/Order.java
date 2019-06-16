package com.tangxc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity(name = "t_order")
public class Order {

    @Id
    private String orderId;
    private String chanId;
    private String productId;
    private String chanUserId;
    private String orderType;
    private String orderStatus;
    private String outerOrderId;
    private BigDecimal amount;
    private String memo;
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date createAt;
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date updateAt;

}
