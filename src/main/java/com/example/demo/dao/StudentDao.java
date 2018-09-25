package com.example.demo.dao;

import com.example.demo.entity.Student;

import java.util.List;

/**
 * @author lanboo
 * @date 2018/9/25 10:41
 */

public interface StudentDao {
    List<Student> findAllStudent();
}
