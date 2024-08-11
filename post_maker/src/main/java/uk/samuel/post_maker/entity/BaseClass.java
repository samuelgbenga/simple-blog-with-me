package uk.samuel.post_maker.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;

@MappedSuperclass
@EnableJpaAuditing
@Getter
@Setter
public abstract class BaseClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime dateTimeCreated;


    @LastModifiedBy
    private LocalDateTime dateTimeModified;

    @PrePersist
    @PreUpdate
    public void prePersist(){
        if(dateTimeCreated == null){
            dateTimeCreated = LocalDateTime.now();
        }
        dateTimeModified = LocalDateTime.now();
    }
}
