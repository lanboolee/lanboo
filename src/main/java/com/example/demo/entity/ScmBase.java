package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class ScmBase implements Serializable {
    private static final long serialVersionUID = 1841646150515368881L;

    protected String id;
    protected Date createTime = new Date();
    protected Date updateTime;
    protected Integer deleteFlag = 0;
    protected String tenantId;
    protected String updateUser;
    protected String updateUserName;
    protected String createUser;
    protected String createUserName;
    protected String token;

}
