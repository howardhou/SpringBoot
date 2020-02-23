package com.example.common.service;

import com.example.common.dto.CommodityDTO;
import com.example.common.response.ObjectResponse;

public interface StorageService {
    /**
     * 扣减库存
     */
    ObjectResponse decreaseStorage(CommodityDTO commodityDTO);
}
