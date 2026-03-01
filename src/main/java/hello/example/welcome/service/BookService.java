package hello.example.welcome.service;

import hello.example.welcome.entity.BookTable;
import hello.example.welcome.repo.BookRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
         if(book.getTotalCopies()<1){
             throw new RuntimeException("minimum add one book");
         }
        var existingOpt=bookRepository.findByBookTitleAndAuthor(
                book.getBookTitle(), book.getAuthor()
        );
        if(existingOpt.isPresent()){
            BookTable existing=existingOpt.get();
            Long totalCopies=book.getTotalCopies();

            existing.setTotalCopies(existing.getTotalCopies()+totalCopies);
            existing.setAvailableCopies(existing.getAvailableCopies()+totalCopies);
            return bookRepository.save(existing);
        }
        return bookRepository.save(book);
    }

    public List<BookTable> addManyBooks(List<BookTable> bookTable){
        if(bookTable.isEmpty()){
            throw new RuntimeException("the bookTable is empty");
        }
        List<BookTable> newBooks=new ArrayList<>();
        for (BookTable table : bookTable) {
            if (table.getBookTitle() == null || table.getBookTitle().isBlank()) {
                throw new RuntimeException("the book title is empty");
            }
            if (table.getAuthor() == null || table.getAuthor().isBlank()) {
                throw new RuntimeException("the author is empty");
            }
            if (table.getTotalCopies() < 1) {
                throw new RuntimeException("add minimum one book");
            }

            var ExistingOpt = bookRepository.findByBookTitleAndAuthor(
                    table.getBookTitle(), table.getAuthor()
            );

            if (ExistingOpt.isPresent()) {
                BookTable existing = ExistingOpt.get();
                Long total = table.getTotalCopies();

                existing.setTotalCopies(existing.getTotalCopies() + total);
                existing.setAvailableCopies(existing.getAvailableCopies() + total);
                bookRepository.save(existing);
            } else {
                newBooks.add(table);
            }
        }
        return bookRepository.saveAll(newBooks);
    }

    public List<BookTable> getBook(String title){
        if(title==null || title.isBlank()){
            throw new RuntimeException("the search box is empty");
        }
        List<BookTable> allBooks=bookRepository.findAll();
        List<BookTable> result=new ArrayList<>();

        for(BookTable book:allBooks){
            if(book.getBookTitle().toLowerCase().contains(title.toLowerCase())){
                result.add(book);
            }
        }
        if(result.isEmpty()){
            throw new RuntimeException("that book is not available");
        }
        return result;
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
