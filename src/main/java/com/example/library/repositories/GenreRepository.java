package com.example.library.repositories;

import com.example.library.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * The GenreRepository interface is responsible for defining database operations
 * for the Genre entity, such as CRUD (Create, Read, Update, Delete) operations.
 */
@Repository
public interface GenreRepository extends JpaRepository<Genre, UUID> {
}
