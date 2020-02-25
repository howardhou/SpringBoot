package com.example.account.service;

import com.example.account.mapper.AccountMapper;
import com.example.common.dto.AccountDTO;
import com.example.common.enums.RspStatusEnum;
import com.example.common.response.ObjectResponse;
import com.example.common.service.AccountService;
import io.seata.core.context.RootContext;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

// 对于非幂等的操作不能使用重试机制
@Service(version = "1.0.0",protocol = "${dubbo.protocol.id}",
        application = "${dubbo.application.id}",registry = "${dubbo.registry.id}",
        timeout = 3000 , retries = 0)
public class AccountServiceImp implements AccountService {

    @Autowired
    AccountMapper accountMapper;

    @Override
    public ObjectResponse decreaseAccount(AccountDTO accountDTO) {

        System.out.println("开始全局事务，XID = " + RootContext.getXID());

        int account = accountMapper.decreaseAccount(accountDTO.getUserId(), accountDTO.getAmount().doubleValue());

        //int a = 10/0;

        ObjectResponse<Object> response = new ObjectResponse<>();
        if (account > 0){
            response.setStatus(RspStatusEnum.SUCCESS.getCode());
            response.setMessage(RspStatusEnum.SUCCESS.getMessage());
            return response;
        }

        response.setStatus(RspStatusEnum.FAIL.getCode());
        response.setMessage(RspStatusEnum.FAIL.getMessage());
        return response;
    }

    @Override
    public void testGlobalLock() {
        accountMapper.testGlobalLock("1");
        System.out.println("Hi, i got lock, i will do some thing with holding this lock.");
    }
}
