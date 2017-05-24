package com.easytoolsoft.easyreport.support.aop;

import java.sql.SQLException;

import javax.validation.ValidationException;

import com.easytoolsoft.easyreport.support.model.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局异常处理器
 *
 * @author Tom Deng
 * @date 2017-04-11
 **/
@Slf4j
@ResponseBody
@ControllerAdvice
public class ExceptionAdvice {
    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseResult handleHttpMessageNotReadableException(final HttpMessageNotReadableException e) {
        log.error("参数解析失败", e);
        return ResponseResult.failure(400, "参数解析失败", e.toString());
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ResponseResult handleValidationException(final ValidationException e) {
        log.error("参数验证失败", e);
        return ResponseResult.failure(400, "参数验证失败", e.toString());
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ResponseResult handleBindException(final BindException e) {
        log.error("参数验证失败", e);
        return ResponseResult.failure(400, "参数验证失败", e.toString());
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SQLException.class)
    public ResponseResult handleSQLException(final SQLException e) {
        log.error("SQL语句执行出错", e);
        return ResponseResult.failure(400, "SQL语句执行出错", e.toString());
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseResult handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException e) {
        log.error("不支持当前请求方法", e);
        return ResponseResult.failure(405, "不支持当前请求方法", e.toString());
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseResult handleHttpMediaTypeNotSupportedException(final Exception e) {
        log.error("不支持当前媒体类型", e);
        return ResponseResult.failure(415, "不支持当前媒体类型", e.toString());
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseResult handleException(final Exception e) {
        log.error("服务运行异常", e);
        return ResponseResult.failure(500, "服务运行异常", e.toString());
    }
}