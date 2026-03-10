package hello.example.welcome.service;

import hello.example.welcome.ExceptionHandling.BookNotFoundException;
import hello.example.welcome.MappingDTO.BookMapper;
import hello.example.welcome.dto.request.BookReqDTO;
import hello.example.welcome.dto.response.BookResDTO;
import hello.example.welcome.entity.BookTable;
import hello.example.welcome.repo.BookRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    public BookResDTO addBook(BookReqDTO bookReqDTO){
        BookTable book=BookMapper.mapToBookTable(bookReqDTO);
        BookTable savedBook;
        if(book.getBookTitle()==null || book.getBookTitle().isBlank()){
            throw new IllegalArgumentException("book Title is required");
        }
        if(book.getAuthor()==null || book.getAuthor().isBlank()){
            throw new IllegalArgumentException("book Author is required");
        }
         if(book.getTotalCopies()<1){
             throw new IllegalArgumentException("minimum add one book");
         }
        var existingOpt=bookRepository.findByBookTitleAndAuthor(
                book.getBookTitle(), book.getAuthor()
        );
        if(existingOpt.isPresent()){
            BookTable existing=existingOpt.get();
            Long totalCopies=book.getTotalCopies();

            existing.setTotalCopies(existing.getTotalCopies()+totalCopies);
            existing.setAvailableCopies(existing.getAvailableCopies()+totalCopies);
            savedBook=bookRepository.save(existing);
            return BookMapper.mapToBookResDTO(savedBook);
        }
        savedBook=bookRepository.save(book);
        return BookMapper.mapToBookResDTO(savedBook);
    }

    public List<BookResDTO> addManyBooks(List<BookReqDTO> bookReqDTOS){
        List<BookTable> bookTable=bookReqDTOS.stream().map(
                BookMapper::mapToBookTable
        ).toList();

        if(bookTable.isEmpty()){
            throw new IllegalArgumentException("the bookTable is empty");
        }
        List<BookTable> resultBooks=new ArrayList<>();
        List<BookTable> newBooks=new ArrayList<>();
        for (BookTable table : bookTable) {
            if (table.getBookTitle() == null || table.getBookTitle().isBlank()) {
                throw new IllegalArgumentException("the book title is empty");
            }
            if (table.getAuthor() == null || table.getAuthor().isBlank()) {
                throw new IllegalArgumentException("the author is empty");
            }
            if (table.getTotalCopies() < 1) {
                throw new IllegalArgumentException("add minimum one book");
            }

            var ExistingOpt = bookRepository.findByBookTitleAndAuthor(
                    table.getBookTitle(), table.getAuthor()
            );

            if (ExistingOpt.isPresent()) {
                BookTable existing = ExistingOpt.get();
                Long total = table.getTotalCopies();

                existing.setTotalCopies(existing.getTotalCopies() + total);
                existing.setAvailableCopies(existing.getAvailableCopies() + total);
                BookTable updatedBook=bookRepository.save(existing);
                resultBooks.add(updatedBook);
            } else {
                newBooks.add(table);
            }
        }
        if(!newBooks.isEmpty()){
            List<BookTable> savedBooks=bookRepository.saveAll(newBooks);
            resultBooks.addAll(savedBooks);
        }
        return resultBooks.stream().map(BookMapper::mapToBookResDTO).toList();
    }

    public List<BookResDTO> getBook(String title)  {
        if(title==null || title.isBlank()){
            throw new IllegalArgumentException("the search box is empty");
        }
        List<BookTable> result=bookRepository.findByBookTitleContainingIgnoreCase(title);

        if(result.isEmpty()){
            throw new BookNotFoundException(title+" book is not available ");
        }
        return result.stream().map(BookMapper::mapToBookResDTO).toList();
    }

    public List<BookResDTO> getAllBook(){
        List<BookTable> lists=bookRepository.findAll(Sort.by("bookTitle"));
        if(lists.isEmpty()){
            throw new BookNotFoundException("books not found");
        }
        return lists.stream().map(BookMapper::mapToBookResDTO).toList();
    }

    public void deleteBook(Long id){
        BookTable book=bookRepository.findById(id)
                        .orElseThrow(()->new BookNotFoundException("book not found :"+id));
        bookRepository.delete(book);
    }

    public void deleteAllBooks(){
        bookRepository.deleteAll();
    }
}
