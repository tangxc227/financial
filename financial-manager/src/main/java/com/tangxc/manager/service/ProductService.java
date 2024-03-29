package com.tangxc.manager.service;

import com.tangxc.entity.Product;
import com.tangxc.entity.enums.ProductStatus;
import com.tangxc.manager.error.ErrorEnum;
import com.tangxc.manager.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        log.debug("创建产品，参数：{}", product);
        // 数据校验
        checkProduct(product);
        // 设置默认值
        setDefault(product);
        Product result = this.productRepository.save(product);
        log.debug("创建产品，结果：{}", result);
        return result;
    }

    /**
     * 查询单个产品
     *
     * @param id 产品编号
     * @return 返还对应产品或者null
     */
    public Product findOne(String id) {
        Assert.notNull(id, "需要产品编号参数");
        log.debug("查询单个产品，id={}", id);
        Product product = this.productRepository.findOne(id);
        log.debug("查询单个产品,结果={}", product);
        return product;
    }

    public Page<Product> query(List<String> idList,
                               BigDecimal minRewardRate,
                               BigDecimal maxRewardRate,
                               List<String> statusList,
                               Pageable pageable) {

        Specification<Product> specification = (root, criteriaQuery, criteriaBuilder) -> {
            Expression<String> idCol = root.get("id");
            Expression<BigDecimal> rewardRateCol = root.get("rewardRate");
            Expression<String> statusCol = root.get("status");
            List<Predicate> predicateList = new ArrayList<>();
            if (null != idList && idList.size() > 0) {
                predicateList.add(idCol.in(idList));
            }
            if (null != minRewardRate && BigDecimal.ZERO.compareTo(minRewardRate) < 0) {
                predicateList.add(criteriaBuilder.ge(rewardRateCol, minRewardRate));
            }
            if (null != maxRewardRate && BigDecimal.ZERO.compareTo(maxRewardRate) < 0) {
                predicateList.add(criteriaBuilder.le(rewardRateCol, maxRewardRate));
            }
            if (null != statusList && statusList.size() > 0) {
                predicateList.add(statusCol.in(statusList));
            }
            criteriaQuery.where(predicateList.toArray(new Predicate[0]));
            return null;
        };
        Page<Product> page = this.productRepository.findAll(specification, pageable);
        return page;
    }

    private void setDefault(Product product) {
        if (product.getCreateAt() == null) {
            product.setCreateAt(new Date());
        }
        if (product.getUpdateAt() == null) {
            product.setUpdateAt(new Date());
        }
        if (product.getCreateUser() == null) {
            product.setCreateUser("system");
        }
        if (product.getUpdateUser() == null) {
            product.setUpdateUser("system");
        }
        if (product.getStepAmount() == null) {
            product.setStepAmount(BigDecimal.ZERO);
        }
        if (product.getLockTerm() == null) {
            product.setLockTerm(0);
        }
        if (product.getStatus() == null) {
            product.setStatus(ProductStatus.AUDITING.name());
        }
        if (product.getMemo() == null) {
            product.setMemo("");
        }
    }

    /**
     * 产品数据校验
     * 1.非空数据
     * 2.收益率要0-30以内
     * 3.投资步长需为整数
     * @param product
     */
    private void checkProduct(Product product) {
        Assert.notNull(product.getId(), ErrorEnum.ID_NOT_NULL.code);
        Assert.isTrue(BigDecimal.ZERO.compareTo(product.getRewardRate()) < 0 && BigDecimal.valueOf(30).compareTo(product.getRewardRate()) >= 0, "收益率范围错误");
        Assert.isTrue(BigDecimal.valueOf(product.getStepAmount().longValue()).compareTo(product.getStepAmount()) == 0, "投资步长需为整数");
    }


}
