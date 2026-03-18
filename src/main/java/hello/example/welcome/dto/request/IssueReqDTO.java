package hello.example.welcome.dto.request;

import hello.example.welcome.entity.Status;
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
public class IssueReqDTO {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String bookTitle;
    @NotBlank
    private LocalDate issueDate;
    @NotBlank
    private Status status;
}
