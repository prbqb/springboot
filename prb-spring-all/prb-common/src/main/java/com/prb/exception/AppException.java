package com.prb.exception;

import lombok.Data;

@Data
public class AppException extends RuntimeException{

    private ExceptionEnum ee;

    public AppException(ExceptionEnum ee){
        super(ee.getMsg());
        this.ee = ee;
    }

}
