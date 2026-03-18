package hello.example.welcome.service;

import hello.example.welcome.MappingDTO.ReturnMapper;
import hello.example.welcome.dto.request.ReturnReqDTO;
import hello.example.welcome.dto.response.IssueResDTO;
import hello.example.welcome.dto.response.ReturnResDTO;
import hello.example.welcome.entity.IssueTable;
import hello.example.welcome.entity.ReturnTable;
import hello.example.welcome.entity.Status;
import hello.example.welcome.repo.IssueRepository;
import hello.example.welcome.repo.ReturnRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReturnBookService {
    private final ReturnRepository returnRepository;
    private final IssueRepository issueRepository;
    public ReturnBookService(ReturnRepository returnRepository,IssueRepository issueRepository){
        this.returnRepository=returnRepository;
        this.issueRepository=issueRepository;
    }

    public ReturnResDTO UserReturnBook(ReturnReqDTO returnReqDTO){
        IssueTable issue=issueRepository.findByIssueId(returnReqDTO.getIssueId());
        ReturnTable returnTable=ReturnMapper.mapToReturnTable(returnReqDTO,issue);

        if(!returnTable.getReturnDate().equals(LocalDate.now())){
            throw new IllegalArgumentException("Invalid Date");
        }
        long days= ChronoUnit.DAYS.between(issue.getDueDate(),returnReqDTO.getReturnDate());
        long fine=(days>0) ? days:0;
        returnTable.setFineAmount(fine);
        issue.setStatus(Status.Returned);
        returnRepository.save(returnTable);
        return ReturnMapper.mapToReturnResDTO(returnTable);
    }

    public List<ReturnResDTO> AllReturnedBooks(){
        return returnRepository.findAll().stream().map(ReturnMapper::mapToReturnResDTO).toList();
    }

    public ReturnResDTO searchReturnedBook(String email){
        ReturnTable returnTable=returnRepository.findByIssueTableStudentTableEmail(email);
        return ReturnMapper.mapToReturnResDTO(returnTable);
    }

    public void deleteReturnedBook(Long id){
        returnRepository.deleteById(id);
    }

    public void deleteAllReturnedBooks(){
        returnRepository.deleteAll();
    }

}


