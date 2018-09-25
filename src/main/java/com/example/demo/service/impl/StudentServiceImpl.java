package com.example.demo.service.impl;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lanboo
 * @date 2018/9/25 10:43
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public List<Student> findAllStudent() {
        return null;
    }
}
