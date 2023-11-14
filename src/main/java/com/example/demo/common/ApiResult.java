package com.example.demo.common;
import java.util.HashMap;

/**
 * 统一返回结果类，继承自 HashMap，用于封装 Web 接口的统一返回格式。
 */
public class ApiResult extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    /**
     * 返回结果状态码的标识键
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回结果消息的标识键
     */
    public static final String MSG_TAG = "msg";

    /**
     * 返回结果数据的标识键
     */
    public static final String DATA_TAG = "data";

    /**
     * 无参构造方法，用于创建空的 AjaxResult 对象。
     */
    public ApiResult() {
    }

    /**
     * 带有状态码和消息的构造方法，用于创建包含状态码和消息的 AjaxResult 对象。
     *
     * @param code 状态码
     * @param msg  返回消息
     */
    public ApiResult(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 带有状态码、消息和数据的构造方法，用于创建包含状态码、消息和数据的 AjaxResult 对象。
     *
     * @param code 状态码
     * @param msg  返回消息
     * @param data 返回数据
     */
    public ApiResult(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (data != null) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 创建成功结果的静态方法，无返回消息和数据。
     *
     * @return 成功结果
     */
    public static ApiResult success() {
        return ApiResult.success("Success");
    }

    /**
     * 创建成功结果的静态方法，包含返回消息。
     *
     * @param msg 返回消息
     * @return 成功结果
     */
    public static ApiResult success(String msg) {
        return ApiResult.success(msg, null);
    }

    /**
     * 创建成功结果的静态方法，包含返回数据。
     *
     * @param data 返回数据
     * @return 成功结果
     */
    public static ApiResult success(Object data) {
        return ApiResult.success("Success", data);
    }

    /**
     * 创建成功结果的静态方法，包含返回消息和数据。
     *
     * @param msg  返回消息
     * @param data 返回数据
     * @return 成功结果
     */
    public static ApiResult success(String msg, Object data) {
        return new ApiResult(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * 创建错误结果的静态方法，无返回消息和数据。
     *
     * @return 错误结果
     */
    public static ApiResult error() {
        return ApiResult.error("Error");
    }

    /**
     * 创建错误结果的静态方法，包含返回消息。
     *
     * @param msg 返回消息
     * @return 错误结果
     */
    public static ApiResult error(String msg) {
        return ApiResult.error(msg, null);
    }

    /**
     * 创建错误结果的静态方法，包含返回消息和数据。
     *
     * @param msg  返回消息
     * @param data 返回数据
     * @return 错误结果
     */
    public static ApiResult error(String msg, Object data) {
        return new ApiResult(HttpStatus.ERROR, msg, data);
    }

    /**
     * 创建错误结果的静态方法，包含状态码和返回消息。
     *
     * @param code 状态码
     * @param msg  返回消息
     * @return 错误结果
     */
    public static ApiResult error(int code, String msg) {
        return new ApiResult(code, msg, null);
    }
}

