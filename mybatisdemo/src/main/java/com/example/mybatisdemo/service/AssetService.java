package com.example.mybatisdemo.service;

import com.example.mybatisdemo.dao.AssetDao;
import com.example.mybatisdemo.dto.ResultModel;
import com.example.mybatisdemo.entity.Asset;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {
    private final AssetDao assetDao;

    // 不建议使用属性注入, 建议使用构造注入的方式进行bean的注入
    @Autowired
    public AssetService(AssetDao assetDao) {
        this.assetDao = assetDao;
    }

    public ResultModel<Asset> getAssetById(Integer id){
        Asset asset = assetDao.getAssetById(id);
        return ResultModel.success(asset);
    }

    public ResultModel<List<Asset>> getAssetsByUserId(Integer userId){
        List<Asset> list = assetDao.getAssetsByUserId(userId);

        return ResultModel.success(list);
    }

    public ResultModel<PageInfo<Asset>> getAssetsByUserId(Integer userId, Integer pageNo, Integer pageSize){
        PageHelper.startPage(pageNo, pageSize, true);
        List<Asset> list = assetDao.getAssetsByUserId(userId);
        PageInfo<Asset> pageInfo = new PageInfo<>(list);

        return ResultModel.success(pageInfo);
    }
}
