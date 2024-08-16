package uk.samuel.post_maker.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class PushNotification extends BaseClass{

    private String token;


}
