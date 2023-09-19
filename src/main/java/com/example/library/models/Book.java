package com.example.library.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "books")
public class Book extends ApplicationEntity<Book> {
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer pages;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String summary;

    @Column(nullable = false)
    private User user;
}
