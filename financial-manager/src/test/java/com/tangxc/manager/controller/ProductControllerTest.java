package com.tangxc.manager.controller;

import com.tangxc.entity.Product;
import com.tangxc.entity.enums.ProductStatus;
import com.tangxc.util.RestUtil;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    private static RestTemplate restTemplate = new RestTemplate();

    @Value("http://localhost:${local.server.port}/manager")
    private String baseUrl;

    private static List<Product> normals = new ArrayList<>();

    @BeforeClass
    public static void init() {
        Product product1 = new Product();
        product1.setId("T001");
        product1.setName("灵活宝1号");
        product1.setStatus(ProductStatus.AUDITING.desc);
        product1.setThresholdAmount(BigDecimal.valueOf(10));
        product1.setStepAmount(BigDecimal.valueOf(1));
        product1.setRewardRate(BigDecimal.valueOf(3.42));
        Product product2 = new Product();
        product2.setId("T002");
        product2.setName("活期赢-金色人生");
        product2.setStatus(ProductStatus.AUDITING.desc);
        product2.setThresholdAmount(BigDecimal.valueOf(10));
        product2.setStepAmount(BigDecimal.valueOf(0));
        product2.setRewardRate(BigDecimal.valueOf(3.28));
        Product product3 = new Product();
        product3.setId("T003");
        product3.setName("朝朝盈-聚财");
        product3.setStatus(ProductStatus.AUDITING.desc);
        product3.setThresholdAmount(BigDecimal.valueOf(100));
        product3.setStepAmount(BigDecimal.valueOf(10));
        product3.setRewardRate(BigDecimal.valueOf(3.86));
        normals.add(product1);
        normals.add(product2);
        normals.add(product3);
    }

    @Test
    public void create() {
        normals.forEach(product -> {
            Product result = RestUtil.postJSON(restTemplate, baseUrl + "/products", product, Product.class);
            Assert.notNull(result.getCreateAt(), "插入失败");
        });
    }

}
