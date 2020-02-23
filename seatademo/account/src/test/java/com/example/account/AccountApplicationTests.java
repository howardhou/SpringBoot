package com.example.account;

import com.example.common.dto.AccountDTO;
import com.example.common.response.ObjectResponse;
import com.example.common.service.AccountService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class AccountApplicationTests {

    @Autowired
    AccountService accountService;

    Logger logger = LoggerFactory.getLogger(AccountApplicationTests.class);
    @Test
    void contextLoads() {

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUserId("1");
        accountDTO.setAmount(BigDecimal.valueOf(10.0));

        ObjectResponse response = accountService.decreaseAccount(accountDTO);

        logger.info("test: "+ response);
    }

}
