package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value="base_book")
public class Book  {
    @TableId(value="id")
    private Long id;
    @TableField("archived")
    private Boolean archived;

    @TableField("version")
    private Integer version;

    @TableField("created_on")
    private Date createdOn;

    @TableField("updated_on")
    private Date updatedOn;

    @TableField("attrs")
    private String attrs;

    @TableField("book_code")
    private String bookCode;

    @TableField("book_name")
    private String bookName;

    @TableField("inventory_quantity")
    private Integer inventoryQuantity;

    @TableField("created_by")
    private Long createdBy;

    @TableField("updated_by")
    private Long updatedBy;
}
