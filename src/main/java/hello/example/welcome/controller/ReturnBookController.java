package hello.example.welcome.controller;

import hello.example.welcome.dto.request.ReturnReqDTO;
import hello.example.welcome.dto.response.ReturnResDTO;
import hello.example.welcome.service.ReturnBookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/returnBook")
public class ReturnBookController {
    private final ReturnBookService returnBookService;
    public ReturnBookController(ReturnBookService returnBookService){
        this.returnBookService=returnBookService;
    }

    @PostMapping("/userReturnBook")
    public ReturnResDTO returnBook(@RequestBody ReturnReqDTO returnReqDTO){
        return returnBookService.UserReturnBook(returnReqDTO);
    }

    @GetMapping("/AllReturnedBooks")
    public List<ReturnResDTO> getAll(){
        return returnBookService.AllReturnedBooks();
    }

    @GetMapping("/Search")
    public ReturnResDTO search(@RequestParam String email){
        return returnBookService.searchReturnedBook(email);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll(){
        returnBookService.deleteAllReturnedBooks();
        return "All returned Books are deleted";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        returnBookService.deleteReturnedBook(id);
        return id+" is deleted";
    }
}
