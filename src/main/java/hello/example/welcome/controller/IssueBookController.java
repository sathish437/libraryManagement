package hello.example.welcome.controller;

import hello.example.welcome.dto.request.IssueReqDTO;
import hello.example.welcome.dto.response.IssueResDTO;
import hello.example.welcome.entity.BookTable;
import hello.example.welcome.service.IssueBookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/IssueBook")
public class IssueBookController {
        private final IssueBookService issueBookService;
        public IssueBookController(IssueBookService issueBookService){
            this.issueBookService=issueBookService;
        }

        @PostMapping("/issueBook")
        public IssueResDTO StdIssueBook(@RequestBody IssueReqDTO issueReqDTO){
            return issueBookService.stdBookIssue(issueReqDTO);
        }

        @GetMapping("/AllIssueBooks")
        public List<IssueResDTO> ListOfIssuesBooks(){
            return issueBookService.getAllIssues();
        }

        @GetMapping("/searchUser")
        public IssueResDTO searchUserIssues(@RequestParam String Email){
            return issueBookService.searchEmail(Email);
        }

        @DeleteMapping("/delete/{id}")
        public String DeleteOne(@PathVariable Long id){
            issueBookService.deleteOneIssue(id);
            return "deleted this id : "+id;
        }

        @DeleteMapping("/deleteAllIssueDatas")
        public String DeleteAll(){
            issueBookService.deleteAllIssues();
            return "Delete All Issues";
        }
}
