package uk.samuel.post_maker.payload.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.samuel.post_maker.entity.UserEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestDto {

    private String postTitle;

    private String post;



}
