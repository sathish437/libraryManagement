package hello.example.welcome.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @Column(nullable = false,length = 100)
    private String bookTitle;
    @Column(nullable = false,length = 100)
    private String author;
    @Column(nullable = false)
    @Min(1)
    private Long totalCopies;
    @Min(1)
    @Column(nullable = false)
    private Long availableCopies;


}
