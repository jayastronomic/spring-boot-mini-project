package com.example.library.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * The Book class represents a book entity in the library application.
 * It extends the ApplicationEntity class to inherit common fields and functionality.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "books")
public class Book extends ApplicationEntity<Book> {
    /**
     * The title of the book.
     */
    @Column(nullable = false)
    @NotBlank(message = "Title can not be blank")
    private String title;

    /**
     * The number of pages in the book.
     */
    @NotNull(message = "Pages cannot be null")
    @Min(value = 1, message = "Pages should be greater than or equal to 1")
    private Integer pages;

    /**
     * The author of the book.
     */
    @Column(nullable = false)
    @NotBlank(message = "Author can not be blank")
    private String author;

    /**
     * A summary of the book.
     */
    @Column(nullable = false, columnDefinition = "text")
    @NotBlank(message = "Summary can not be blank")
    private String summary;

    /**
     * The user who owns the book.
     */
    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne
    private User user;

    /**
     * The list of genres associated with the book.
     */
    @ManyToMany
    @JoinTable(
            name = "books_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

    /**
     * Updates the book entity with data from another book payload.
     *
     * @param payload The Book object containing updated data.
     */
    @Override
    public void update(Book payload) {
        setTitle(payload.getTitle());
        setSummary(payload.getSummary());
        setPages(payload.getPages());
        setAuthor(payload.getAuthor());
    }
}
