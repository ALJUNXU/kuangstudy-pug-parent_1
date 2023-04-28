package com.pug.resultex.handler;

import com.pug.resultex.domain.ErrorHandler;
import com.pug.resultex.ex.BussinessException;
import com.pug.resultex.ex.OrderException;
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
        return ErrorHandler.error(500, ex.getMessage(), request.getRequestURL().toString());
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
