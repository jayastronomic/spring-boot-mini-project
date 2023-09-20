package com.example.library.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

/**
 * The User class represents a user entity in the library application.
 * It extends the ApplicationEntity class to inherit common fields and functionality.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "users")
public final class User extends ApplicationEntity<User> {
    /**
     * The username of the user.
     */
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Username can not be blank")
    @Size(min = 2, max = 300, message = "Username must be between 2 and 300 characters")
    private String username;

    /**
     * The email address of the user.
     */
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email can not be blank")
    @Email(message = "Invalid email format")
    @Size(max = 300, message = "Email must be less than 300 characters")
    private String email;

    /**
     * The password of the user.
     */

    @Column(nullable = false)
    @NotBlank(message = "Password can not be blank")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * The list of books associated with the user.
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Book> books;

    /**
     * Updates the user entity with data from another user payload.
     *
     * @param payload The User object (payload) containing updated data.
     */
    @Override
    public void update(User payload) {
        setUsername(payload.getUsername());
    }

    /**
     * Finds a book associated with the user by its unique identifier.
     *
     * @param id The unique identifier of the book to find.
     * @return The Book object if found, or null if not found.
     */
    public Book findBookById(UUID id){
        return books
                .stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
