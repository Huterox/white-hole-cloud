package com.huterox.whitehole.whiteholecommunity.exception;

import com.huterox.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestControllerAdvice(basePackages = "com.huterox.whitehole.whiteholecommunity.controller")
public class CommunityExceptionControllerAdvice {

    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public R handleVaildException(MethodArgumentNotValidException e){
        log.error("数据校验出现问题{}，异常类型：{}",e.getMessage(),e.getClass());
        BindingResult bindingResult = e.getBindingResult();

        Map<String,String> errorMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach((fieldError)->{
            errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
        });
        return R.error(BizCodeEnum.VAILD_EXCEPTION.getCode(),BizCodeEnum.VAILD_EXCEPTION.getMsg()).put("data",errorMap);
    }

    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable){
        //拦截所有的异常
        log.error("错误：",throwable);
        return R.error(BizCodeEnum.UNKNOW_EXCEPTION.getCode(),BizCodeEnum.UNKNOW_EXCEPTION.getMsg());
    }

}
