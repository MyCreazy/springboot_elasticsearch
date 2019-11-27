package com.tjh.springboot_elasticsearch.service;

import com.tjh.springboot_elasticsearch.model.StudentTestVo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface StudentService {
    String addStudent(StudentTestVo studentTestVo);

    List<StudentTestVo> queryStudent(String name);

    List<StudentTestVo> queryStudentByAge(String age,int optType);

    void deleteStudent(int id);
}
