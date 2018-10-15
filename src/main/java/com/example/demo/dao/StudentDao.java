package com.example.demo.dao;

import com.example.demo.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lanboo
 * @date 2018/9/25 10:41
 */
@Repository
public interface StudentDao {
    List<Student> findAllStudent();
    List<Student> findTest();
}
