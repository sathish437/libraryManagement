package hello.example.welcome.controller;

import hello.example.welcome.dto.request.StudentReqDTO;
import hello.example.welcome.dto.response.StudentResDTO;
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
    public List<StudentResDTO> addManyStudentsList(@Valid @RequestBody List<StudentReqDTO> studentList){
        return studentService.addManyStudent(studentList);
    }

    @PostMapping("/addStudent")
    public StudentResDTO studentAdd(@Valid @RequestBody StudentReqDTO student){
        return studentService.addStudent(student);
    }

    @GetMapping("/getStudents")
    public List<StudentResDTO> fetchAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/search")
    public List<StudentResDTO> searchStudents(@RequestParam("name") String name){
        return studentService.studentSearch(name);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAllStudents(){
        studentService.deleteAllStudent();
        return "All students details are deleted";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return id+" student is deleted";
    }

}