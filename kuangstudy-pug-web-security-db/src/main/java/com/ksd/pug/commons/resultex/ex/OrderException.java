package com.ksd.pug.commons.resultex.ex;//package com.kuangstudy.config;


import com.ksd.pug.commons.resultex.enums.ResultStatusEnum;

/**
 * Description: 自定义异常
 * Author: yykk Administrator
 * Version: 1.0
 * Create Date Time: 2021/12/15 21:48.
 * Update Date Time:
 *
 * @see
 */
public class OrderException extends RuntimeException {

    private Integer status;
    private String msg;

    public OrderException(Integer status, String msg) {
        super(msg);
        this.msg = msg;
        this.status = status;
    }

    public OrderException(String msg) {
        super(msg);
        this.status = 500;
        this.msg = msg;
    }

    public OrderException(ResultStatusEnum resultStatusEnum) {
        super(resultStatusEnum.getMessage());
        this.status = resultStatusEnum.getStatus();
        this.msg = resultStatusEnum.getMessage();
    }


    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

}
