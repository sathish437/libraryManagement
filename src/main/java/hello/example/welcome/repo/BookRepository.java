package hello.example.welcome.repo;

import hello.example.welcome.entity.BookTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookTable,Long> {
    Optional<BookTable> findByBookTitleAndAuthor(String bookTitle,String author);
    Optional<BookTable> findByBookTitle(String bookTitle);
}
