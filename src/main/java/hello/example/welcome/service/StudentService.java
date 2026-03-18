package hello.example.welcome.service;

import hello.example.welcome.ExceptionHandling.EmailAlreadyExistsException;
import hello.example.welcome.ExceptionHandling.StudentNotFoundException;
import hello.example.welcome.MappingDTO.BookMapper;
import hello.example.welcome.MappingDTO.StudentMapper;
import hello.example.welcome.dto.request.StudentReqDTO;
import hello.example.welcome.dto.response.StudentResDTO;
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

    public List<StudentResDTO> addManyStudent(List<StudentReqDTO> studentReqDTOS){
        List<StudentTable> studentsList= studentReqDTOS.stream().map(StudentMapper::mapToStudentTable).toList();
        if(studentsList.isEmpty()){
            throw new IllegalArgumentException("the student is empty");
        }

        List<StudentTable> newStudents=new ArrayList<>();
        for(StudentTable student:studentsList){
            if(student.getName()==null||student.getEmail()==null|| student.getName().isEmpty() || student.getEmail().isEmpty() || student.getDepartment()==null){
                throw new IllegalArgumentException("Enter the Specified value");
            }
            StudentTable existingEmail=studentRepository.findByEmail(student.getEmail());
            if(existingEmail!=null){
                throw new EmailAlreadyExistsException("that email already Exist");
            }
            newStudents.add(student);
        }
        List<StudentTable> result=studentRepository.saveAll(newStudents);
        return result.stream().map(StudentMapper::mapToStudentResDTO).toList();
    }

    public StudentResDTO addStudent(StudentReqDTO studentReqDTO){
        StudentTable student=StudentMapper.mapToStudentTable(studentReqDTO);

        StudentTable existingEmail=studentRepository.findByEmail(student.getEmail());
        if(existingEmail !=null){
            throw new EmailAlreadyExistsException("that email already Exists");
        }
        StudentTable result=studentRepository.save(student);
        return StudentMapper.mapToStudentResDTO(result);
    }

    public List<StudentResDTO> getAllStudents(){
        List<StudentTable> students=studentRepository.findAll();
        if(students.isEmpty()){
            throw new StudentNotFoundException("Students Not Found");
        }
        return students.stream().map(StudentMapper::mapToStudentResDTO).toList();
    }

    public List<StudentResDTO> studentSearch(String name){
        List<StudentTable> nameList = studentRepository.findByNameContainingIgnoreCase(name);
        if(nameList.isEmpty()){
            throw new StudentNotFoundException("that student not found");
        }
        return nameList.stream().map(StudentMapper::mapToStudentResDTO).toList();
    }



    public void deleteAllStudent(){
        studentRepository.deleteAll();
    }

    public void deleteStudent(Long id){
        StudentTable student=studentRepository.findById(id)
                        .orElseThrow(()-> new StudentNotFoundException(id+" not found"));
        studentRepository.delete(student);
    }
}
