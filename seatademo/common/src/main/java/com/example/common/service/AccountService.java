package com.example.common.service;

import com.example.common.dto.AccountDTO;
import com.example.common.response.ObjectResponse;

public interface AccountService {
    /**
     * 扣用户钱
     */
    ObjectResponse decreaseAccount(AccountDTO accountDTO);

    void testGlobalLock();
}
