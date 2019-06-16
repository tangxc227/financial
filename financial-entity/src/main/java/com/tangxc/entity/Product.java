package com.tangxc.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品
 */
@Data
@Entity(name = "t_product")
public class Product implements Serializable {

    @Id
    private String id;
    private String name;
    private BigDecimal thresholdAmount;
    private BigDecimal stepAmount;
    private Integer lockTerm;
    private BigDecimal rewardRate;
    /**
     * @see com.tangxc.entity.enums.ProductStatus
     */
    private String status;
    private String memo;
    private Date createAt;
    private String createUser;
    private Date updateAt;
    private String updateUser;

}
