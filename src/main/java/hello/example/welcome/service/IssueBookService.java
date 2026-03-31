package hello.example.welcome.service;

import hello.example.welcome.ExceptionHandling.BookNotFoundException;
import hello.example.welcome.ExceptionHandling.DeletionNotAllowedException;
import hello.example.welcome.ExceptionHandling.InvalidEmailException;
import hello.example.welcome.ExceptionHandling.StudentNotFoundException;
import hello.example.welcome.MappingDTO.IssueMapper;
import hello.example.welcome.dto.request.IssueReqDTO;
import hello.example.welcome.dto.response.IssueResDTO;
import hello.example.welcome.entity.BookTable;
import hello.example.welcome.entity.IssueTable;
import hello.example.welcome.entity.Status;
import hello.example.welcome.entity.StudentTable;
import hello.example.welcome.repo.BookRepository;
import hello.example.welcome.repo.IssueRepository;
import hello.example.welcome.repo.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class IssueBookService {
    private final IssueRepository issueRepository;
    private final StudentRepository studentRepository;
    private final BookRepository bookRepository;
    public IssueBookService(IssueRepository issueRepository,StudentRepository studentRepository,BookRepository bookRepository){
        this.issueRepository=issueRepository;
        this.studentRepository=studentRepository;
        this.bookRepository=bookRepository;
    }

    public IssueResDTO stdBookIssue(IssueReqDTO issueReqDTO){
        if(issueReqDTO.getEmail()==null || issueReqDTO.getBookTitle()==null || issueReqDTO.getEmail().isBlank() || issueReqDTO.getBookTitle().isBlank() ||  issueReqDTO.getStatus()==null ){
            throw new IllegalArgumentException("All required fields are must be provided");
        }
        if(!issueReqDTO.getIssueDate().equals(LocalDate.now())){
            throw new IllegalArgumentException("Issue Date must be today");
        }
        StudentTable student=studentRepository.findByEmail(issueReqDTO.getEmail());
        boolean emailExist=studentRepository.existsByEmail(issueReqDTO.getEmail());
        if(!emailExist){
            throw new StudentNotFoundException("Student Not found");
        }
        BookTable book=bookRepository.findByBookTitle(issueReqDTO.getBookTitle());
        if(book==null){
            throw new BookNotFoundException("Book Not found");
        }
        IssueTable issue=IssueMapper.mapToIssueTable(issueReqDTO,student,book);
        if(book.getAvailableCopies()<=0){
            throw new RuntimeException("Book Not Available");
        }
        book.setAvailableCopies(book.getAvailableCopies()-1);
        issue.setDueDate(issueReqDTO.getIssueDate().plusDays(30));
        issueRepository.save(issue);
        return IssueMapper.mapToIssueResDTO(issue);
    }
    
    public List<IssueResDTO> getAllIssues(){
        List<IssueTable> issue=issueRepository.findByStatus(Status.Issued);
        return issue.stream().map(IssueMapper::mapToIssueResDTO).toList();
    }
    public List<IssueResDTO> searchName(String name){
        List<IssueTable> issue=issueRepository.findByStudentTableNameContainingIgnoreCase(name);
        return issue.stream().map(IssueMapper::mapToIssueResDTO).toList();
    }

    public void deleteOneIssue(Long id){
        IssueTable issue=issueRepository.findById(id)
                        .orElseThrow(()->new RuntimeException(id+" Not Found"));
        if(issue.getStatus()!= Status.Returned){
            throw new DeletionNotAllowedException("Student cannot be deleted until all issued books are returned");        }
        issueRepository.delete(issue);
    }

    public void deleteAllIssues(){
        List<IssueTable> issue=issueRepository.findAll();
        for(IssueTable issueTable:issue){
            if(issueTable.getStatus()!= Status.Returned){
                throw new DeletionNotAllowedException("Student cannot be deleted until all issued books are returned");            }
        }

        issueRepository.deleteAll();
    }

}
