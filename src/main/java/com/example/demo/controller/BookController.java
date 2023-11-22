package com.example.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("book list")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("list")
    public List<Book> getUsers(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {

        return bookService.list(pageNum, pageSize);
    }
    }

