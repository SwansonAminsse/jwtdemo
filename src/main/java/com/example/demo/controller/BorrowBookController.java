package com.example.demo.controller;

import com.example.demo.Jwt.JwtRequired;
import com.example.demo.Jwt.JwtUtil;
import com.example.demo.common.ApiResult;
import com.example.demo.entity.Borrow;
import com.example.demo.service.BorrowBookService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("borrow book")
public class BorrowBookController {
    @Autowired
    private BorrowBookService borrowBookService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    })
    @JwtRequired
    @PostMapping("borrow")
    public ApiResult borrowBook(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String token,String bookName, String bookCode) {
        String userName = JwtUtil.getClaim(token, JwtUtil.ACCOUNT);
        return borrowBookService.borrowBook(userName, bookName, bookCode);
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    })
    @JwtRequired
    @PostMapping("return")
    public ApiResult returnBook(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String token,String bookName, String bookCode) {
        String userName = JwtUtil.getClaim(token, JwtUtil.ACCOUNT);
        return borrowBookService.returnBook(userName, bookName, bookCode);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    })
    @JwtRequired
    @PostMapping("borrowList")
    public List<Borrow> borrowList(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String token) {
        String userName = JwtUtil.getClaim(token, JwtUtil.ACCOUNT);
        return borrowBookService.getBorrowRecords(userName);
    }
}
