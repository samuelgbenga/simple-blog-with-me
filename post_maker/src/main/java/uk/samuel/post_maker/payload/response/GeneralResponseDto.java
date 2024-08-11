package uk.samuel.post_maker.payload.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponseDto {

    private String responseCode;

    private String responseMessage;
}
