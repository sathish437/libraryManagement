package hello.example.welcome.service;

import hello.example.welcome.entity.StudentTable;
import hello.example.welcome.repo.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }

    public List<StudentTable> addManyStudent(List<StudentTable> studentsList){
        return studentRepository.saveAll(studentsList);
    }

    public StudentTable addStudent(StudentTable student){
        return studentRepository.save(student);
    }

    public List<StudentTable> getAllStudents(){
        return studentRepository.findAll();
    }

    public Optional<StudentTable> getStudent(Long id){
        return studentRepository.findById(id);
    }

    public void deleteAllStudent(){
        studentRepository.deleteAll();
    }

    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }
}
