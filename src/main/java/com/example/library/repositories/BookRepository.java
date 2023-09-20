package com.example.library.repositories;

import com.example.library.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * The BookRepository interface is responsible for defining database operations
 * for the Book entity, such as CRUD (Create, Read, Update, Delete) operations.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
}
