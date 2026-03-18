package hello.example.welcome.dto.response;

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
public class ReturnResDTO {
    @NotNull
    private Long returnId;
    @NotNull
    private Long issueId;
    @NotBlank
    @Email
    private String Email;
    @NotNull
    private LocalDate returnDate;
    @NotNull
    private Long fineAmount;
}
