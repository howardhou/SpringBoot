package com.example.account.controller;

import com.example.common.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    AccountService accountService;

    @GetMapping("/test_global_lock")
    void testGlobalLock() {
        LOGGER.info("testGlobalLock");
        accountService.testGlobalLock();
    }
}
