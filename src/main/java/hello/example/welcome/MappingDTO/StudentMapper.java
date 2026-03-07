package hello.example.welcome.MappingDTO;

import hello.example.welcome.dto.request.StudentReqDTO;
import hello.example.welcome.dto.response.StudentResDTO;
import hello.example.welcome.entity.StudentTable;

public class StudentMapper {
    public static StudentTable mapToStudentTable(StudentReqDTO studentReqDTO){
        StudentTable student=new StudentTable();
        student.setName(studentReqDTO.getName());
        student.setEmail(studentReqDTO.getEmail());
        student.setDepartment(studentReqDTO.getDepartment());
        return student;
    }
    public static StudentResDTO mapToStudentResDTO(StudentTable studentTable){
        StudentResDTO studentResDTO=new StudentResDTO();
        studentResDTO.setStudentId(studentTable.getStudentId());
        studentResDTO.setName(studentTable.getName());
        studentResDTO.setEmail(studentTable.getEmail());
        studentResDTO.setDepartment(studentTable.getDepartment());
        return studentResDTO;
    }
}
