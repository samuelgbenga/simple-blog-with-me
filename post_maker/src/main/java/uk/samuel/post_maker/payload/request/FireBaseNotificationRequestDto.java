package uk.samuel.post_maker.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FireBaseNotificationRequestDto {

    private String title;
    private String body;
    private String topic;
    private String token;
}
