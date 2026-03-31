package hello.example.welcome.service;

import hello.example.welcome.ExceptionHandling.InvalidEmailException;
import hello.example.welcome.ExceptionHandling.IssueBookNotFoundException;
import hello.example.welcome.MappingDTO.ReturnMapper;
import hello.example.welcome.dto.request.ReturnReqDTO;
import hello.example.welcome.dto.response.IssueResDTO;
import hello.example.welcome.dto.response.ReturnResDTO;
import hello.example.welcome.entity.BookTable;
import hello.example.welcome.entity.IssueTable;
import hello.example.welcome.entity.ReturnTable;
import hello.example.welcome.entity.Status;
import hello.example.welcome.repo.BookRepository;
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
    private final BookRepository bookRepository;
    public ReturnBookService(ReturnRepository returnRepository,IssueRepository issueRepository,BookRepository bookRepository){
        this.returnRepository=returnRepository;
        this.issueRepository=issueRepository;
        this.bookRepository=bookRepository;
    }

    public ReturnResDTO UserReturnBook(ReturnReqDTO returnReqDTO){
        IssueTable issue=issueRepository.findByIssueId(returnReqDTO.getIssueId());
        BookTable book=bookRepository.findByBookTitle(issue.getBookTable().getBookTitle());
        ReturnTable returnTable=ReturnMapper.mapToReturnTable(returnReqDTO,issue);
        if(issue==null){
            throw new IssueBookNotFoundException("Issues Book Not found");
        }
        if(!returnTable.getReturnDate().equals(LocalDate.now())){
            throw new IllegalArgumentException("Invalid Date");
        }
        long days= ChronoUnit.DAYS.between(issue.getDueDate(),returnReqDTO.getReturnDate());
        long fine=(days>0) ? days:0;
        book.setAvailableCopies(book.getAvailableCopies()+1);
        returnTable.setFineAmount(fine);
        issue.setStatus(Status.Returned);
        returnRepository.save(returnTable);
        return ReturnMapper.mapToReturnResDTO(returnTable);
    }

    public List<ReturnResDTO> AllReturnedBooks(){
        return returnRepository.findAll().stream().map(ReturnMapper::mapToReturnResDTO).toList();
    }

    public List<ReturnResDTO> searchReturnedBook(String name){

        List<ReturnTable> returnTable=returnRepository.findByIssueTableStudentTableName(name);

        return returnTable.stream().map(ReturnMapper::mapToReturnResDTO).toList();
    }

    public void deleteReturnedBook(Long id){

        ReturnTable returnTable= returnRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException(id+" Not Found"));
        returnRepository.delete(returnTable);
    }
    public void deleteAllReturnedBooks(){
        returnRepository.deleteAll();
    }

}


