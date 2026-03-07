package hello.example.welcome.controller;

import hello.example.welcome.dto.request.BookReqDTO;
import hello.example.welcome.dto.response.BookResDTO;
import hello.example.welcome.entity.BookTable;
import hello.example.welcome.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getAllBooks")
    public List<BookResDTO> getAllBooks() {
        return bookService.getAllBook();
    }

    @GetMapping("/searchBooks")
    public List<BookResDTO> getBooks(@RequestParam("bookTitle") String title) {
        return bookService.getBook(title);
    }

    @PostMapping("/addBooks")
    public List<BookResDTO> addManyBook(@RequestBody List<BookReqDTO> bookTable) {
        return bookService.addManyBooks(bookTable);
    }

    @PostMapping("/addBook")
    public BookResDTO addBook(@RequestBody BookReqDTO bookTable) {
        return bookService.addBook(bookTable);
    }

    @DeleteMapping("/DeleteAll")
    public String deleteAll() {
        bookService.deleteAllBooks();
        return "All books are deleted";
    }

    @DeleteMapping("/Delete/{id}")
    public String delete(@PathVariable Long id){
        bookService.deleteBook(id);
        return "That book is deleted";
    }
}