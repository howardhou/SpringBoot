package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.entity.Asset;
import com.example.mybatisdemo.jpa.UserEntity;
import com.example.mybatisdemo.service.AssetService;
import com.example.mybatisdemo.service.UserService;
import com.example.mybatisdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    public Asset getAssetById(@PathVariable Integer id){
        return assetService.getAssetById(id);
    }

    @GetMapping("/getAssetsByUser/{userId}")
    public List<Asset> getAssetByUserId(@PathVariable Integer userId){
        return assetService.getAssetsByUserId(userId);
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

    @GetMapping("/getAllUserEntity")
    public List<UserEntity> getAllUserEntity() {
        return userService.getAllUserEntity();
    }
}
