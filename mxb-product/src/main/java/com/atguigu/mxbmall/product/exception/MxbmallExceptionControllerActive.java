package com.atguigu.mxbmall.product.exception;

import com.atguigu.common.exception.BizCodeEnume;
import com.atguigu.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 集中处理所有异常
 */

@Slf4j
@RestControllerAdvice(basePackages = "com.atguigu.mxbmall.product.controller")
public class MxbmallExceptionControllerActive {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleVaildException(MethodArgumentNotValidException e) {
        log.error("数据校验出现问题{},异常类型{}",e.getMessage(), e.getClass());
        BindingResult  bindingResult = e.getBindingResult();
        Map<String, String> errorMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach((fieldError)->{
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return R.error(BizCodeEnume.VALID_EXCEPTION.getCode(), BizCodeEnume.UNKNOW_EXEPTION.getMsg()).put("data", errorMap);
    }

    /**
     * 处理 other Exception
     * @param throwable
     * @return
     */
    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable) {
        //return R.error();
        return R.error(BizCodeEnume.UNKNOW_EXEPTION.getCode(), BizCodeEnume.UNKNOW_EXEPTION.getMsg());
    }

}
