package com.example.security.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-03-10 19:52
 * @Description:
 */
@ControllerAdvice
public class BaseException {

    private Logger logger = LoggerFactory.getLogger(BaseException.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        logger.error("Base Exception" +  e.getMessage());
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }

}
