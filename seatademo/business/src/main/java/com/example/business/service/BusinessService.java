package com.example.business.service;

import com.example.common.dto.BusinessDTO;
import com.example.common.response.ObjectResponse;

public interface BusinessService {
    ObjectResponse handleBusiness(BusinessDTO businessDTO);
}
