package hello.example.welcome.controller;

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

    @GetMapping("/getBooks")
    public List<BookTable> getAllBooks() {
        return bookService.getAllBook();
    }

    @GetMapping("/getBooks")
    public List<BookTable> getBooks(@RequestParam String title) {
        return bookService.getBook(title);
    }

    @PostMapping("/addBooks")
    public List<BookTable> addManyBook(@RequestBody List<BookTable> bookTable) {
        return bookService.addManyBooks(bookTable);
    }

    @PostMapping("/addBook")
    public BookTable addBook(@RequestBody BookTable bookTable) {

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