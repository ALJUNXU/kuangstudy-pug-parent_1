package com.ksd.pug.commons.resultex.enums;

/**
 * Description:
 * Author: yykk Administrator
 * Version: 1.0
 * Create Date Time: 2021/12/16 21:05.
 * Update Date Time:
 *
 * @see
 */
public enum ResultStatusEnum {

    SUCCESS_STATUS(200, "SUCCESS"),
    USER_PWR_STATUS(601, "用户密码有误"),
    ORDER_ERROR_STATUS(602, "订单有误");

    ResultStatusEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    private Integer status;
    private String message;

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
