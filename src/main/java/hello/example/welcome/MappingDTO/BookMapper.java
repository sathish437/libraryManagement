package hello.example.welcome.MappingDTO;

import hello.example.welcome.dto.request.BookReqDTO;
import hello.example.welcome.dto.response.BookResDTO;
import hello.example.welcome.entity.BookTable;

public class BookMapper {
    public static BookTable mapToBookTable(BookReqDTO bookReqDTO){
        BookTable book = new BookTable();
        book.setBookTitle(bookReqDTO.getBookTitle());
        book.setAuthor(bookReqDTO.getAuthor());
        book.setTotalCopies(bookReqDTO.getTotalCopies());
        book.setAvailableCopies(bookReqDTO.getTotalCopies());
        return book;
    }
    public static BookResDTO mapToBookResDTO(BookTable bookTable){
        BookResDTO bookResDTO=new BookResDTO();
        bookResDTO.setBookId(bookTable.getBookId());
        bookResDTO.setBookTitle(bookTable.getBookTitle());
        bookResDTO.setAuthor(bookTable.getAuthor());
        bookResDTO.setAvailableBooks(bookTable.getAvailableCopies());
        return bookResDTO;
    }
}
