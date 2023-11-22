package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value="base_personnel")
public class User implements Serializable {

    @TableId(value="id")
    private Long id;

    private String user_name;

    private String password;

    private String permissions;

    private static final long serialVersionUID = 1L;

}
