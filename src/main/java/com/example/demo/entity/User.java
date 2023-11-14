package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value="user")
public class User implements Serializable {

    @TableId(value="id")
    private Long id;

    private String name;

    private String password;

    private String roleIds;

    private static final long serialVersionUID = 1L;

}
