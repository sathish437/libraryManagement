package hello.example.welcome.MappingDTO;

import hello.example.welcome.dto.request.IssueReqDTO;
import hello.example.welcome.dto.response.IssueResDTO;
import hello.example.welcome.entity.BookTable;
import hello.example.welcome.entity.IssueTable;
import hello.example.welcome.entity.StudentTable;

public class IssueMapper {
    public static IssueTable mapToIssueTable(IssueReqDTO issueReqDTO,StudentTable student,BookTable book){
        IssueTable issueTable=new IssueTable();

        issueTable.setStudentTable(student);
        issueTable.setBookTable(book);
        issueTable.setIssueDate(issueReqDTO.getIssueDate());
        issueTable.setStatus(issueReqDTO.getStatus());
        return issueTable;
    }

    public static IssueResDTO mapToIssueResDTO(IssueTable issueTable){
        IssueResDTO issueResDTO=new IssueResDTO();

        issueResDTO.setIssueId(issueTable.getIssueId());
        issueResDTO.setStudentId(issueTable.getStudentTable().getStudentId());
        issueResDTO.setStudentName(issueTable.getStudentTable().getName());
        issueResDTO.setBookId(issueTable.getBookTable().getBookId());
        issueResDTO.setBookTitle(issueTable.getBookTable().getBookTitle());
        issueResDTO.setIssueDate(issueTable.getIssueDate());
        issueResDTO.setDueDate(issueTable.getDueDate());
        issueResDTO.setStatus(issueTable.getStatus());
        return issueResDTO;
    }
}
