package com.example.demo.controller;

import com.example.demo.Jwt.JwtRequired;

import com.example.demo.Jwt.JwtUtil;
import com.example.demo.common.ApiResult;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("adminLogin")

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")

    public ApiResult login(@RequestBody UserDto userDto){
        ApiResult apiResult = new ApiResult();
        apiResult = userService.login(userDto);
        return apiResult;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    })
    @JwtRequired
    @PostMapping("change")
    public ApiResult change(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String token, @RequestParam String oldPassword, @RequestParam String newPassword){
        String user_name = JwtUtil.getClaim(token, JwtUtil.ACCOUNT);
                return userService.change(user_name,oldPassword,newPassword);
    }
}

