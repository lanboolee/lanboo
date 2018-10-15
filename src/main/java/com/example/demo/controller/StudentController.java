package com.example.demo.controller;

import com.example.demo.service.StudentService;
import com.example.demo.utils.MessageBody;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lanboo
 * @date 2018/9/25 10:45
 */
@RestController
@ComponentScan(basePackages = "com.example.demo")
@MapperScan("com.example.demo.dao")
public class StudentController {

    @Autowired
    private StudentService studentService;

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @PostMapping("findAll")
    public MessageBody findAll(){
        LOGGER.info("查询所有信息");
        return MessageBody.getMessageBody(studentService.findAllStudent());
    }
    @GetMapping("findTest")
    public MessageBody findTest(){
        return MessageBody.getMessageBody(studentService.findTest());
    }
    @PostMapping("error")
    public int error(){
        int i = 1/0;
        return  i;
    }

}
