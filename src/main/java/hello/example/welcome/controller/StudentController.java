package hello.example.welcome.controller;

import hello.example.welcome.entity.StudentTable;
import hello.example.welcome.service.StudentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }
    @PostMapping("/addStudents")
    public List<StudentTable> addManyStudentsList(@RequestParam List<StudentTable> studentList){
        return studentService.addManyStudent(studentList);
    }

}