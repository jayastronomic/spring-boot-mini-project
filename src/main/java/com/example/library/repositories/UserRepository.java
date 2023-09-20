package com.example.library.repositories;

import com.example.library.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

/**
 * The UserRepository interface is responsible for defining database operations
 * for the User entity, such as CRUD (Create, Read, Update, Delete) operations.
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    /**
     * Checks if a user with a given email address exists.
     *
     * @param email The email address to check.
     * @return true if a user with the specified email exists, false otherwise.
     */
    boolean existsByEmail(@NotBlank(message = "Email can not be blank") @Email(message = "Invalid email format") String email);

    /**
     * Finds a user by their email address.
     *
     * @param email The email address to search for.
     * @return The User object if found, or null if not found.
     */
    User findByEmail(String email);
}
