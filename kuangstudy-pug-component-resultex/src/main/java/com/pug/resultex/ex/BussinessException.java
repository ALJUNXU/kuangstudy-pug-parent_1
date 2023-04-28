package com.pug.resultex.ex;//package com.kuangstudy.config;


import com.pug.resultex.enums.ResultStatusEnum;

/**
 * Description: 自定义异常
 * Author: yykk Administrator
 * Version: 1.0
 * Create Date Time: 2021/12/15 21:48.
 * Update Date Time:
 *
 * @see
 */
public class BussinessException extends RuntimeException {

    private Integer status;
    private String msg;

    public BussinessException(Integer status, String msg) {
        super(msg);
        this.msg = msg;
        this.status = status;
    }

    public BussinessException(ResultStatusEnum resultStatusEnum) {
        super(resultStatusEnum.getMessage());
        this.status = resultStatusEnum.getStatus();
        this.msg = resultStatusEnum.getMessage();
    }

    public BussinessException(String msg) {
        super(msg);
        this.status = 500;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

}
