package com.itheima.service;

import com.itheima.controller.Exception401;
import com.itheima.dto.UserInfoDto;
import com.itheima.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void login(String username, String password) {
        boolean success = userMapper.login(username, password);
        if (!success) {
            throw new Exception401("用户登录失败!");
        }
    }

    public UserInfoDto findUserInfo(String username) {
        return userMapper.findUserInfo(username);
    }

    public void updateUserInfo(UserInfoDto dto) {
        userMapper.updateUserInfo(dto);
    }
}
