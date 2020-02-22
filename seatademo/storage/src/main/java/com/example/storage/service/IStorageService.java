package com.example.storage.service;

import com.example.common.dto.CommodityDTO;
import com.example.common.response.ObjectResponse;

public interface IStorageService {
    /**
     * 扣减库存
     */
    ObjectResponse decreaseStorage(CommodityDTO commodityDTO);
}
