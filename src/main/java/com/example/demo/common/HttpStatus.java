package com.example.demo.common;

/**
 * 定义常用的 HTTP 状态码常量，用于表示 Web 请求的结果状态。
 */
public class HttpStatus {

    // 成功
    public static final int SUCCESS = 200;

    // 已创建
    public static final int CREATED = 201;

    // 已接受
    public static final int ACCEPTED = 202;

    // 无内容
    public static final int NO_CONTENT = 204;

    // 永久移动
    public static final int MOVED_PERM = 301;

    // 查看其他位置
    public static final int SEE_OTHER = 303;

    // 未修改
    public static final int NOT_MODIFIED = 304;

    // 错误的请求
    public static final int BAD_REQUEST = 400;

    // 未经授权
    public static final int UNAUTHORIZED = 401;

    // 禁止访问
    public static final int FORBIDDEN = 403;

    // 未找到
    public static final int NOT_FOUND = 404;

    // 方法不被允许
    public static final int BAD_METHOD = 405;

    // 冲突
    public static final int CONFLICT = 409;

    // 不支持的媒体类型
    public static final int UNSUPPORTED_TYPE = 415;

    // 服务器错误
    public static final int ERROR = 500;

    // 未实现
    public static final int NOT_IMPLEMENTED = 501;
}

