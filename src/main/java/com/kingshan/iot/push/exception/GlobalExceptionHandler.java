package com.kingshan.iot.push.exception;

import com.kingshan.iot.push.api.vo.Result;
import com.kingshan.iot.push.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * <p>
 *  全局异常捕获处理
 * </p>
 *
 * @author: Kingshan
 * @since: 2019/11/7 15:57
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(UserNotFindException.class)
    public Result<?> handleUNFException(UserNotFindException e){
        return Result.error(CommonConstant.SC_ERROR_USER_NOT_FOUND_301,e.getMessage());
    }


    @ExceptionHandler(GroupNotFindException.class)
    public Result<?> handleGNFxception(GroupNotFindException e){
        return Result.error(CommonConstant.SC_ERROR_GROUP_NOT_FOUND_302,e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e){
        log.error(e.getMessage(), e);
        return Result.error("操作失败，"+e.getMessage());
    }

    /**
     * 处理Spring校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handleRRException(MethodArgumentNotValidException e){
        List<ObjectError> errorList=e.getBindingResult().getAllErrors();
        String error="";
        for (ObjectError objectError : errorList) {
            error += objectError.getDefaultMessage() +" ";
        }
        log.error(e.getMessage(), e);
        return Result.error(error);
    }

}
