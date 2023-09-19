package com.example.library.models;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.UUID;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class ApplicationEntity<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;


}
