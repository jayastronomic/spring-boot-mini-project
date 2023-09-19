package com.example.library.models;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Data
@MappedSuperclass
public class ApplicationEntity<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;
}
