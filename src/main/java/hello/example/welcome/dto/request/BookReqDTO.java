package hello.example.welcome.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookReqDTO {
;
    @NotBlank @Size(min = 2,max=100)
     private String title;
    @NotBlank @Size(min = 2 ,max = 100)
     private String author;
    @NotNull @Min(1)
     private Long availableCopies;
}
