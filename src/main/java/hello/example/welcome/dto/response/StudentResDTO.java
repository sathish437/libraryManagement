package hello.example.welcome.dto.response;

import hello.example.welcome.entity.Department;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentResDTO {
    @NotNull
    private Long studentId;
    @NotBlank
    private String name;
    @Email
    @NotBlank
    private String email;
    @NotNull
    private Department department;
}
