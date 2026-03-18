package hello.example.welcome.MappingDTO;

import hello.example.welcome.dto.request.ReturnReqDTO;
import hello.example.welcome.dto.response.ReturnResDTO;
import hello.example.welcome.entity.IssueTable;
import hello.example.welcome.entity.ReturnTable;

public class ReturnMapper {
    public static ReturnTable mapToReturnTable(ReturnReqDTO returnReqDTO, IssueTable issue){
        ReturnTable  returns=new ReturnTable();

        returns.setIssueTable(issue);
        returns.setReturnDate(returnReqDTO.getReturnDate());
        return returns;
    }
    public static ReturnResDTO mapToReturnResDTO(ReturnTable returnTable){
        ReturnResDTO returnResDTO=new ReturnResDTO();

        returnResDTO.setReturnId(returnTable.getReturnId());
        returnResDTO.setIssueId(returnTable.getIssueTable().getIssueId());
        returnResDTO.setEmail(returnTable.getIssueTable().getStudentTable().getEmail());
        returnResDTO.setReturnDate(returnTable.getReturnDate());
        returnResDTO.setFineAmount(returnTable.getFineAmount());

        return returnResDTO;
    }
}

