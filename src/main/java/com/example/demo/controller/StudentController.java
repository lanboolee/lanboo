package com.example.demo.controller;

import com.example.demo.service.StudentService;
import com.example.demo.utils.MessageBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lanboo
 * @date 2018/9/25 10:45
 */
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @PostMapping("findAll")
    public MessageBody findAll(){
        LOGGER.error("sldkjf");
        return MessageBody.getMessageBody(studentService.findAllStudent());
    }
}
