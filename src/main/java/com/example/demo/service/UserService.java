package com.example.demo.service;

import com.example.demo.common.ApiResult;
import com.example.demo.common.BusinessException;
import com.example.demo.common.CommonResult;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.Jwt.JwtUtil;

import static com.example.demo.Jwt.JwtUtil.ACCOUNT;

@Service
@Transactional
public class UserService  {

    @Autowired
    private UserMapper userMapper;



    public ApiResult login(UserDto userInfo) {
        User uInfo = userMapper.getUserByLogin(userInfo.getName());
        if (null == uInfo) {
            throw new BusinessException(CommonResult.USERNAME_ERROR);
        } else if (!userInfo.getPassword().equals(uInfo.getPassword())) {
            throw new BusinessException(CommonResult.PASSWORD_ERROR);
        }
        System.out.println("Thread ID: " + Thread.currentThread().getId());
        String token = JwtUtil.sign(userInfo.getName(),String.valueOf(System.currentTimeMillis()));
        return ApiResult.success("userInfo", token);
    }
    public ApiResult change(String name,String oldPassword, String newPassword) {
        User uInfo = userMapper.getUserByLogin(name);
        if (null == uInfo) {
            throw new BusinessException(CommonResult.USERNAME_ERROR);
        } else if (!oldPassword.equals(uInfo.getPassword())) {
            throw new BusinessException(CommonResult.PASSWORD_ERROR);
        } else {
            uInfo.setPassword(newPassword);
            userMapper.updateByPrimaryKeySelective(uInfo);
            return ApiResult.success("修改成功");
        }
    }
}