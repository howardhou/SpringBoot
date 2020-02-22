package com.example.mybatisdemo.dao;

import com.example.mybatisdemo.entity.Asset;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetDao {
    List<Asset> getAssetsByUserId(int userId);
    Asset getAssetById(int id);
}
