package com.prb.exception;

import com.prb.constants.AppConstants;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum ExceptionEnum {

    SYSTEM_SUCCESS(200, "系统成功"),
    SYSTEM_ERROR(500, "系统错误"),
    USER_ID_MUST_MORE_THEN_TEN(501, "用户id不能超过10"),
    USER_AGE_IS_TOO_BIG(502,"用户的年龄实在是太大了");


    private Integer code;
    private String msg;


    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    public String code2msg(Integer code) {
        for (ExceptionEnum value : values()) {
            if (value.code.equals(code)) {
                return value.msg;
            }
        }
        return "";
    }

    public void eachEnum() {
        for (ExceptionEnum value : values()) {
            log.info("code is : {} value is : {}", value.code, value.msg);
        }

    }
}
