package com.example.storage;

import com.example.common.dto.CommodityDTO;
import com.example.common.response.ObjectResponse;
import com.example.common.service.StorageService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StorageApplicationTests {

    @Autowired
    StorageService storageService;

    Logger logger = LoggerFactory.getLogger(StorageApplicationTests.class);

    @Test
    void contextLoads() {
        CommodityDTO commodityDTO = new CommodityDTO();
        commodityDTO.setCommodityCode("C201901140001");
        commodityDTO.setCount(1);
        ObjectResponse response = storageService.decreaseStorage(commodityDTO);

        logger.info("test - response : " + response);
    }

}
