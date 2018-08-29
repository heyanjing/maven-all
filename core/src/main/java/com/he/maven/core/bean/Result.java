package com.he.maven.core.bean;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.beanutils.BeanUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by heyanjing on 2018/8/24 13:59.
 */
@Getter
@Setter
public class Result extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * // 成功标志
     */
    private boolean success;
    /**
     * // 返回标示
     */
    private Integer code;
    /**
     * // 相关消息
     */
    private String msg;
    /**
     * // 相关数据
     */
    private Object data;
    /**
     * // 错误详细
     */
    private Map<String, Object> errors;

    public Result() {
        super();
    }

    private Result(boolean success) {
        this.success = success;
    }

    public Result(boolean success, Integer code, Object data, String msg) {
        this(success);
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Result(Integer code, Map<String, Object> errors, String msg) {
        this.success = false;
        this.code = code;
        this.errors = errors;
        this.msg = msg;
    }

    public boolean hasErrors() {
        return this.errors != null && this.errors.size() > 0;
    }

    public static Result newResult() {
        return new Result();
    }

    public static Result newResult(Map<String, Object> properties) throws Exception {
        Result result = newResult();
        BeanUtils.populate(result, properties);
        return result;
    }

    //*********************************************************业务调用成功*******************************************************************************************************************************

    public static Result success() {
        return success(null, null);
    }

    public static Result success(Integer code) {
        return success(code, null);
    }

    public static Result success(String msg) {
        return success(null, msg);
    }

    private static Result success(Integer code, String msg) {
        return successWithData(code, null, msg);
    }

    public static Result successWithData(Object data) {
        return successWithData(null, data, null);
    }

    public static Result successWithData(Object data, String msg) {
        return successWithData(null, data, msg);
    }

    public static Result successWithData(Integer code, Object data) {
        return successWithData(code, data, null);
    }

    private static Result successWithData(Integer code, Object data, String msg) {
        return new Result(true, code, data, msg);
    }

    //*********************************************************业务调用失败*******************************************************************************************************************************

    public static Result failure() {
        return failure(null, null);
    }

    public static Result failure(Integer code) {
        return failure(code, null);
    }

    public static Result failure(String msg) {
        return failure(null, msg);
    }

    public static Result failure(Integer code, String msg) {
        return failureWithData(code, null, msg);
    }

    public static Result failureWithData(Object data) {
        return failureWithData(null, data, null);
    }

    public static Result failureWithData(Object data, String msg) {
        return failureWithData(null, data, msg);
    }

    public static Result failureWithData(Integer code, Object data) {
        return failureWithData(code, data, null);
    }

    private static Result failureWithData(Integer code, Object data, String msg) {
        return new Result(false, code, data, msg);
    }
    //*********************************************************代码执行失败:返回包含错误提示*******************************************************************************************************************************

    public static Result failureWithError(Throwable ex) {
        return failureWithError(null, null, ex.getMessage());
    }

    public static Result failureWithError(String field, String msg) {
        Map<String, Object> errors = new HashMap<>();
        errors.put(field, msg);
        return failureWithError(null, errors, msg);
    }

    public static Result failureWithError(Map<String, Object> errors, String msg) {
        return failureWithError(null, errors, msg);
    }

    public static Result failureWithError(Map<String, Object> errors) {
        return failureWithError(null, errors, null);
    }

    public static Result failureWithError(Integer code, Map<String, Object> errors, String msg) {
        return new Result(code, errors, msg);
    }
}
