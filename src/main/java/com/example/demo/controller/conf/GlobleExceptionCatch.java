package com.example.demo.controller.conf;

import com.example.demo.utils.MessageBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lanboo
 * @date 2018/9/30 16:30
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
public class GlobleExceptionCatch {
    private Logger logger = LoggerFactory.getLogger(GlobleExceptionCatch.class);

    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public MessageBody defaltErrorHandler(HttpServletRequest request,Throwable t){
        MessageBody body = MessageBody.getErrorMessageBody("500","系统出错啦");
        return body;
    }
}
