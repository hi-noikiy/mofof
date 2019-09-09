package com.mofof.config;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mofof.constant.Codes;
import com.mofof.util.Json;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //不满足@RequiresGuest注解时抛出的异常信息
    private static final String GUEST_ONLY = "Attempting to perform a guest-only operation";


    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public Json handleShiroException(ShiroException e) {
    e.printStackTrace();
        String eName = e.getClass().getSimpleName();
        log.error("Shiro error {}",eName);
        return new Json(false, Codes.SHIRO_ERR, "authentication/authorization error", null);
    }

    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseBody
    public Json page401(UnauthenticatedException e) {
        String eMsg = e.getMessage();
        if (StringUtils.startsWithIgnoreCase(eMsg,GUEST_ONLY)){
            return new Json(false, Codes.UNAUTHEN, "guest only", null)
                    .data("detail",e.getMessage());
        }else{
            return new Json(false, Codes.UNAUTHEN, "not login", null)
                    .data("detail",e.getMessage());
        }
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public Json page403() {
        return new Json(false, Codes.UNAUTHZ, "no permission", null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Json handleException(Exception e) {
    e.printStackTrace();
        String eName = e.getClass().getSimpleName();
    log.error("Exception error {}", eName);
        return new Json(false, Codes.SHIRO_ERR, "Exception", null);
    }
}