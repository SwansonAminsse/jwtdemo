package com.example.demo.controller;

import com.example.demo.Jwt.JwtRequired;
import com.example.demo.Jwt.JwtUtil;
import com.example.demo.common.ApiResult;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.Jwt.JwtUtil.ACCOUNT;

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
    public ApiResult change(@RequestParam String username,@RequestParam String oldPassword, @RequestParam String newPassword){

        return userService.change(username,oldPassword,newPassword);
    }
}

