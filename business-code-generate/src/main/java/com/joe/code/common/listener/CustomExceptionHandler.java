package com.joe.code.common.listener;


import com.joe.code.common.enums.ResultStatusEnum;
import com.joe.code.common.utils.ResultObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;

//全局异常捕捉处理
@Slf4j
@ControllerAdvice
@RestController
public class CustomExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultObject errorHandler(MethodArgumentNotValidException ex) {
        Iterator var2 = ex.getBindingResult().getAllErrors().iterator();
        String reason = null;
        while(var2.hasNext()) {
            ObjectError error = (ObjectError)var2.next();
            reason = error.getDefaultMessage();
        }
        return ResultStatusEnum.FAIL.backMsg(reason);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResultObject errorHandler(IllegalArgumentException ex) {

        return ResultStatusEnum.FAIL.backMsg(ex.getMessage());
    }
}
