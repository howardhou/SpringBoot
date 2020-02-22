package com.example.mybatisdemo.jpa;

import com.example.mybatisdemo.jpa.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
