package com.example.library.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "books")
public class Book extends ApplicationEntity<Book> {
    @Column(nullable = false)
    @NotBlank(message = "Title can not be blank")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "Pages can not be blank")
    private Integer pages;

    @Column(nullable = false)
    @NotBlank(message = "Author can not be blank")
    private String author;

    @Column(nullable = false)
    @NotBlank(message = "Summary can not be blank")
    private String summary;

    @Column(nullable = false)
    private User user;
}
