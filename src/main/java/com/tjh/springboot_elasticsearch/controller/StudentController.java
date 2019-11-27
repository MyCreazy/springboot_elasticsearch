package com.tjh.springboot_elasticsearch.controller;

import com.alibaba.fastjson.JSON;
import com.tjh.springboot_elasticsearch.model.StudentTestVo;
import com.tjh.springboot_elasticsearch.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping(path = "/add_student")
    public String addStudent(@RequestBody StudentTestVo studentTestVo) {
        String result = "";
        studentService.addStudent(studentTestVo);
        return result;
    }

    @GetMapping(path = "/query_student_byname")
    public String queryStudentByName(@RequestParam String name) {
        String result = "";
        List<StudentTestVo> studentTestVoList = studentService.queryStudent(name);
        result = JSON.toJSONString(studentTestVoList);
        return result;
    }

    @GetMapping(path = "/query_student_byage")
    public String queryStudentByage(@RequestParam String age,@RequestParam int optType) {
        String result = "";
        List<StudentTestVo> studentTestVoList = studentService.queryStudentByAge(age,optType);
        result = JSON.toJSONString(studentTestVoList);
        return result;
    }
}
