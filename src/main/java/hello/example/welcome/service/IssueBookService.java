package hello.example.welcome.service;

import hello.example.welcome.ExceptionHandling.BookNotFoundException;
import hello.example.welcome.ExceptionHandling.InvalidEmailException;
import hello.example.welcome.ExceptionHandling.StudentNotFoundException;
import hello.example.welcome.MappingDTO.IssueMapper;
import hello.example.welcome.dto.request.IssueReqDTO;
import hello.example.welcome.dto.response.IssueResDTO;
import hello.example.welcome.entity.BookTable;
import hello.example.welcome.entity.IssueTable;
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
        if(student== null){
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
        return issueRepository.findAll().stream().map(IssueMapper::mapToIssueResDTO).toList();
    }


    public IssueResDTO searchEmail(String Email){
        if(Email.isBlank() ){
            throw new IllegalArgumentException("Is Empty");
        }
        if(!Email.endsWith("@gmail.com")){
            throw new InvalidEmailException("Invalid EmailId");
        }
        IssueTable issue=issueRepository.findByStudentTableEmail(Email);
        return IssueMapper.mapToIssueResDTO(issue);
    }

    public void deleteOneIssue(Long id){
        IssueTable issue=issueRepository.findById(id)
                        .orElseThrow(()->new RuntimeException(id+" Not Found"));

        issueRepository.delete(issue);
    }

    public void deleteAllIssues(){
        issueRepository.deleteAll();
    }
}
