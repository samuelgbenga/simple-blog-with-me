package uk.samuel.post_maker.payload.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupRequestDto {

    private String fullName;

    private String username;

    private String email;

    private String phoneNumber;

    private String password;
}
