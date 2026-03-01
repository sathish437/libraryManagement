package hello.example.welcome.service;

import hello.example.welcome.entity.BookTable;
import hello.example.welcome.repo.BookRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    public BookTable addBook(BookTable book){
        if(book.getBookTitle()==null || book.getBookTitle().isBlank()){
            throw new RuntimeException("book Title is required");
        }
        if(book.getAuthor()==null || book.getAuthor().isBlank()){
            throw new RuntimeException("book Author is required");
        }
        var existingOpt=bookRepository.findByBookTitleAndAuthor(
                book.getBookTitle(), book.getAuthor()
        );
        if(existingOpt.isPresent()){
            BookTable existing=existingOpt.get();
            Long totalCopies=book.getTotalCopies();
            Long addAvailable=book.getAvailableCopies();

            existing.setTotalCopies(existing.getTotalCopies()+totalCopies);
            existing.setAvailableCopies(existing.getAvailableCopies()+addAvailable);
            return bookRepository.save(existing);
        }
        return bookRepository.save(book);
    }

    public List<BookTable> addManyBooks(List<BookTable> bookTable){
        return bookRepository.saveAll(bookTable);
    }

    public BookTable getBook(Long id){

        return bookRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Book Not Found"));
    }

    public List<BookTable> getAllBook(){
        List<BookTable> lists=bookRepository.findAll(Sort.by("bookTitle"));
        if(lists.isEmpty()){
            throw new RuntimeException("No books found");
        }
        return lists;
    }

    public void deleteBook(Long id){
        BookTable book=bookRepository.findById(id)
                        .orElseThrow(()->new RuntimeException("book not found :"+id));
        bookRepository.delete(book);

    }

    public void deleteAllBooks(){
        bookRepository.deleteAll( );
    }
}
