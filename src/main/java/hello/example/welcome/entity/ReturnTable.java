package hello.example.welcome.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class ReturnTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long returnId;
    @OneToOne
    @JoinColumn(name = "issueId")
    IssueTable issueTable;
    @NotNull
    private LocalDate returnDate;
    private Long fineAmount;
}
