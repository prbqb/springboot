package com.prb.pojo;

import com.prb.constants.AppConstants;
import com.prb.exception.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AppResult  implements Serializable {

    public Integer code;

    private String msg;

    private Object obj;

    private Long stamp;


    public static AppResult SUCCESS(Object obj){

        return new AppResult(ExceptionEnum.SYSTEM_SUCCESS.getCode(),ExceptionEnum.SYSTEM_SUCCESS.getMsg(),obj,System.currentTimeMillis());
    }

    public static AppResult FAIL(Object obj){

        return new AppResult(ExceptionEnum.SYSTEM_ERROR.getCode(),ExceptionEnum.SYSTEM_ERROR.getMsg(),obj,System.currentTimeMillis());
    }

    public static AppResult APPRESULT(Integer code,String msg,Object obj){

        return APPRESULT(code,msg,obj,System.currentTimeMillis());
    }

    public static AppResult APPRESULT(Integer code,String msg,Object obj,Long stamp){

        return new AppResult(code,msg,obj,stamp);
    }

}
