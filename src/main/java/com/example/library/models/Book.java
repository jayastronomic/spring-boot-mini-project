package com.example.library.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "books")
public class Book extends ApplicationEntity<Book> {
    @Column(nullable = false)
    @NotBlank(message = "Title can not be blank")
    private String title;

    @NotNull(message = "Pages cannot be null")
    @Min(value = 1, message = "Pages should be greater than or equal to 1")
    private Integer pages;

    @Column(nullable = false)
    @NotBlank(message = "Author can not be blank")
    private String author;

    @Column(nullable = false, columnDefinition = "text")
    @NotBlank(message = "Summary can not be blank")
    private String summary;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne
    private User user;
}
