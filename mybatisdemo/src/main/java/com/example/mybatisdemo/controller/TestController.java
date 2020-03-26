package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.dto.ResultModel;
import com.example.mybatisdemo.dto.ResultStatus;
import com.example.mybatisdemo.entity.Asset;
import com.example.mybatisdemo.jpa.UserEntity;
import com.example.mybatisdemo.service.AssetService;
import com.example.mybatisdemo.service.UserService;
import com.example.mybatisdemo.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestController {
    private final UserService userService;

    private final AssetService assetService;

    @Autowired
    public TestController(UserService userService, AssetService assetService) {
        this.userService = userService;
        this.assetService = assetService;
    }

    // MyBatis
    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable int id) {
        //System.out.println("test :id: "+id);
        return userService.getUserById(id);
    }

    @GetMapping("/getUserWithAssets/{id}")
    public User getUserWithAssetsById(@PathVariable int id) {
        return userService.getUserWithAssetsById(id);
    }

    @GetMapping("/getAsset/{id}")
    public ResultModel<Asset> getAssetById(@PathVariable Integer id){
        return assetService.getAssetById(id);
    }

    @GetMapping("/getAssetsByUser/{userId}")
    public ResultModel<List<Asset>> getAssetByUserId(@PathVariable Integer userId){
        return assetService.getAssetsByUserId(userId);
    }

    @GetMapping("/getAssetsByUserId")
    public ResultModel<PageInfo<Asset>> getAssetsByUserId(Integer userId, Integer pageNo, Integer pageSize){

        if (ObjectUtils.isEmpty(userId)){
            return ResultModel.error(ResultStatus.PARAMETER_MISS);
        }

        pageNo = ObjectUtils.isEmpty(pageNo) ? 1 : pageNo;
        pageSize = ObjectUtils.isEmpty(pageSize) ? 20 : pageSize;

        if(0 >= userId || 1 > pageNo || 0 >= pageSize) {
            return ResultModel.error(ResultStatus.PARAMETER_ERROR);
        }

        PageHelper.startPage(pageNo, pageSize);
        return assetService.getAssetsByUserId(userId, pageNo, pageSize);
    }

    // JDBCTemplate
    @GetMapping("/getAllUser")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    // Jpa
    @GetMapping("/getUserEntity/{id}")
    public UserEntity getUserEntityById(@PathVariable int id){
        return userService.getUserEntityById(id);
    }
    //jpa
    @GetMapping("/getAllUserEntity")
    public List<UserEntity> getAllUserEntity() {
        return userService.getAllUserEntity();
    }
}
