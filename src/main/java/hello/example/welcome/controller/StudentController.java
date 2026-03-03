package hello.example.welcome.controller;

import hello.example.welcome.entity.StudentTable;
import hello.example.welcome.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }
    @PostMapping("/addStudents")
    public List<StudentTable> addManyStudentsList(@Valid @RequestBody List<StudentTable> studentList){
        return studentService.addManyStudent(studentList);
    }

    @PostMapping("/addStudent")
    public StudentTable studentAdd(@Valid @RequestBody StudentTable student){
        return studentService.addStudent(student);
    }

    @GetMapping("/getStudents")
    public List<StudentTable> fetchAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/search")
    public List<StudentTable> searchStudents(@RequestParam String name){
        return studentService.studentSearch(name);
    }

}