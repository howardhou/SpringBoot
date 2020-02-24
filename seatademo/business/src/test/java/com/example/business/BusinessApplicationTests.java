package com.example.business;

import com.example.business.service.BusinessService;
import com.example.common.dto.BusinessDTO;
import com.example.common.response.ObjectResponse;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class BusinessApplicationTests {

    @Autowired
    BusinessService businessService;

    Logger logger = LoggerFactory.getLogger(BusinessApplicationTests.class);

    @Test
    void contextLoads() {
        BusinessDTO businessDTO = new BusinessDTO();
        businessDTO.setCommodityCode("C201901140001");
        businessDTO.setCount(2);
        businessDTO.setUserId("1");
        businessDTO.setAmount(BigDecimal.valueOf(20));

        ObjectResponse response = businessService.handleBusiness(businessDTO);

        logger.info("test business " + response);
    }

}
