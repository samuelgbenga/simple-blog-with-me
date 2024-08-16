package uk.samuel.post_maker.payload.request;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PushNotificationRequestDto {

    private String token;
}
