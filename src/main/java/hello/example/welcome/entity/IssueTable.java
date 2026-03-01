package hello.example.welcome.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class IssueTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issueId;
    @ManyToOne
    @JoinColumn(name = "studentId")
    StudentTable studentTable;
    @ManyToOne
    @JoinColumn(name = "bookId")
    BookTable bookTable;
    @NotNull
    private LocalDate issueDate;
    @NotNull
    private LocalDate dueDate;
    @NotBlank
    @Column(length = 20)
    private String status;
}
