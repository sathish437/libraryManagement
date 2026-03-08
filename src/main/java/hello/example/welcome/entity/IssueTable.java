package hello.example.welcome.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class IssueTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issueId;
    @ManyToOne
    @JoinColumn(name = "studentId")
    private StudentTable studentTable;
    @ManyToOne
    @JoinColumn(name = "bookId")
    private BookTable bookTable;
    @NotNull
    private LocalDate issueDate;
    @NotNull
    private LocalDate dueDate;
    @NotNull
    @Column(length = 20,nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
}
