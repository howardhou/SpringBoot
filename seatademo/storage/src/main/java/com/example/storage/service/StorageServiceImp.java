package com.example.storage.service;

import com.example.common.dto.CommodityDTO;
import com.example.common.enums.RspStatusEnum;
import com.example.common.response.ObjectResponse;
import com.example.common.service.StorageService;
import com.example.storage.mapper.StorageMapper;
import io.seata.core.context.RootContext;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class StorageServiceImp implements StorageService {

    @Autowired
    StorageMapper storageMapper;

    @Override
    public ObjectResponse decreaseStorage(CommodityDTO commodityDTO) {

        System.out.println("Storage : 全局事务，XID = " + RootContext.getXID());

        int storage = storageMapper.decreaseStorage(commodityDTO.getCommodityCode(), commodityDTO.getCount());
        ObjectResponse<Object> response = new ObjectResponse<>();
        if (storage > 0){
            response.setStatus(RspStatusEnum.SUCCESS.getCode());
            response.setMessage(RspStatusEnum.SUCCESS.getMessage());
            return response;
        }

        response.setStatus(RspStatusEnum.FAIL.getCode());
        response.setMessage(RspStatusEnum.FAIL.getMessage());
        return response;
    }
}
