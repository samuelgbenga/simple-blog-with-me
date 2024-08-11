package uk.samuel.post_maker.payload.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupRequestDto {


    @NotBlank(message = "Cannot be blank")
    private String fullName;

    @NotBlank(message = "Cannot be blank")
    private String username;

    @NotBlank(message = "Cannot be blank")
    private String email;

    @NotBlank(message = "Cannot be blank")
    private String phoneNumber;

    @NotBlank(message = "Cannot be blank")
    private String password;
}
