package hello.example.welcome.service;

import hello.example.welcome.entity.StudentTable;
import hello.example.welcome.repo.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }

    public List<StudentTable> addManyStudent(List<StudentTable> studentsList){
        if(studentsList.isEmpty()){
            throw new RuntimeException("the student is empty");
        }
        List<StudentTable> newStudents=new ArrayList<>();
        for(StudentTable student:studentsList){
            if(student.getName()==null||student.getEmail()==null|| student.getName().isEmpty() || student.getEmail().isEmpty() || student.getDepartment()==null){
                throw new RuntimeException("Enter teh Specified value");
            }
            var existingEmail=studentRepository.findByEmail(student.getEmail());
            if(existingEmail.isPresent()){
                throw new RuntimeException("that email already Exist");
            }
            newStudents.add(student);
        }
        return studentRepository.saveAll(newStudents);
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
