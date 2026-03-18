package hello.example.welcome.dto.response;

import hello.example.welcome.entity.BookTable;
import hello.example.welcome.entity.Status;
import hello.example.welcome.entity.StudentTable;
import jakarta.validation.constraints.Email;
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
public class IssueResDTO {
    @NotNull
    private Long issueId;
    @NotNull
    private Long studentId;
    @NotBlank
    private String studentName;
    @NotNull
    private Long bookId;
    @NotBlank
    private String bookTitle;

    @NotNull
    private LocalDate issueDate;
    @NotNull
    private LocalDate dueDate;
    @NotBlank
    private Status status;
}
