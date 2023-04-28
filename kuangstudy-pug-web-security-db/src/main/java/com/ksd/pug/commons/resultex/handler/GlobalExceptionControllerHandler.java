package com.ksd.pug.commons.resultex.handler;

import com.ksd.pug.commons.resultex.domain.ErrorHandler;
import com.ksd.pug.commons.resultex.ex.BussinessException;
import com.ksd.pug.commons.resultex.ex.OrderException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;


/**
 * Description:
 * Author: yykk Administrator
 * Version: 1.0
 * Create Date Time: 2021/12/15 22:37.
 * Update Date Time:
 *
 * @see
 */
@RestControllerAdvice
public class GlobalExceptionControllerHandler {


    /**
     * 拦截所有程序异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ErrorHandler errorHandler(HttpServletRequest request, Exception ex) {
        return ErrorHandler.error(500, "服务器忙中...", request.getRequestURL().toString());
    }

    /**
     * 安全框架的权限异常拦截
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    public ErrorHandler errorHandler(HttpServletRequest request, AccessDeniedException ex) {
        return ErrorHandler.error(600, "权限不足，请联系管理员...", request.getRequestURL().toString());
    }

    /**
     * OrderException异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(value = OrderException.class)
    @ResponseBody
    public ErrorHandler errorHandlerOrex(HttpServletRequest request, OrderException ex) {
        return ErrorHandler.error(ex.getStatus(), ex.getMsg(), request.getRequestURL().toString());
    }

    /**
     * BussinessException异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BussinessException.class)
    @ResponseBody
    public ErrorHandler errorHandlerBex(HttpServletRequest request, BussinessException ex) {
        return ErrorHandler.error(ex.getStatus(), ex.getMsg(), request.getRequestURL().toString());
    }

}
