package com.example.library.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * The Genre class represents a genre entity in the library application.
 * It extends the ApplicationEntity class to inherit common fields and functionality.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "genres")
public class Genre extends ApplicationEntity<Genre> {

    /**
     * The name of the genre.
     */
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Name can not be blank")
    private String name;

    /**
     * The user who manages the genre.
     */
    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne
    private User user;

    /**
     * The list of books associated with this genre.
     */
    @ManyToMany
    private List<Book> books;

    /**
     * Updates the genre entity with data from another genre payload.
     *
     * @param payload The Genre object (payload) containing updated data.
     */
    @Override
    public void update(Genre payload) {
    }
}
