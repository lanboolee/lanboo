package com.example.demo.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
/**
 * //这个注解里配置的是不需要返回给前端的字段，如果后续有需要可以在注解中将对应字段移除即可
 */
@JsonIgnoreProperties(value = { "errorCode", "errorInfo", "stackTrace"})
public class MessageBody<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private Boolean success;
    private String errorCode;
    private String errorInfo;
    private T data;
    private String message;
    private String stackTrace;

    public static MessageBody getMessageBody(boolean success, Object data) {
        MessageBody body = new MessageBody();
        body.setCode("200");
        body.setData(data);
        body.setSuccess(success);
        return body;
    }

    public static MessageBody getMessageBody(boolean success, Object data, String message) {
        MessageBody body = new MessageBody();
        body.setCode("200");
        body.setData(data);
        body.setMessage(message);
        body.setSuccess(success);
        return body;
    }

    public static MessageBody getErrorMessageBody(String errorInfo) {
        MessageBody body = getMessageBody(false);
        body.setCode("200");
        body.setErrorInfo(errorInfo);
        body.setMessage(errorInfo);
        return body;
    }

    /**
     * 返回错误消息体，此方法同时将code取errorCode的值
     * @param errorCode 错误代码
     * @param errorInfo 错误信息描述
     * @return 消息体
     */
    public static MessageBody getErrorMessageBody(String errorCode, String errorInfo ) {
        MessageBody body = getMessageBody(false);
        body.setErrorInfo(errorInfo);
        body.setErrorCode(errorCode);
        body.setCode(errorCode);
        return body;
    }

    public static MessageBody getMessageBody(boolean success) {
        MessageBody body = new MessageBody();
        body.setCode("200");
        body.setSuccess(Boolean.valueOf(success));
        return body;
    }

    public static MessageBody getMessageBody(boolean success, int code) {
        MessageBody body = new MessageBody();
        body.setCode(Objects.toString(Integer.valueOf(code)));
        body.setSuccess(Boolean.valueOf(success));
        return body;
    }

    public static MessageBody getMessageBody(Object data) {
        MessageBody body = new MessageBody();
        body.setCode("200");
        body.setData(data);
        body.setSuccess(Boolean.valueOf(true));
        return body;
    }

}
