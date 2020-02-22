package com.example.mybatisdemo.service;

import com.example.mybatisdemo.dao.AssetDao;
import com.example.mybatisdemo.entity.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {
    private final AssetDao assetDao;

    @Autowired
    public AssetService(AssetDao assetDao) {
        this.assetDao = assetDao;
    }

    public Asset getAssetById(Integer id){
        return assetDao.getAssetById(id);
    }

    public List<Asset> getAssetsByUserId(Integer userId){
        return assetDao.getAssetsByUserId(userId);
    }
}
