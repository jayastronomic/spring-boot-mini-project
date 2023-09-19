package com.example.library.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "books")
public class Book extends ApplicationEntity<Book> {
    private String title;
    private Integer pages;
    private String author;
    private String summary;
    private User user;
}
