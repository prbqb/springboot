package com.prb.handler;

import com.prb.exception.AppException;
import com.prb.exception.ExceptionEnum;
import com.prb.pojo.AppResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@Slf4j
public class AppExceptionHandler {


    @ExceptionHandler(AppException.class)
    public AppResult handlerSystemException(AppException exception){
      log.info("exception message is : {}", exception.getMessage());
      log.info("exception message object is : {}",AppResult.APPRESULT(exception.getEe().getCode(),exception.getMessage(),exception.getMessage()));
        return AppResult.APPRESULT(exception.getEe().getCode(),exception.getMessage(),exception.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public AppResult handlerRunTimeException(RuntimeException exception){
        log.info("exception message is : {}",exception.getMessage());
        return AppResult.APPRESULT(ExceptionEnum.SYSTEM_SUCCESS.getCode(),ExceptionEnum.SYSTEM_ERROR.getMsg(),exception.getMessage());
    }

}
