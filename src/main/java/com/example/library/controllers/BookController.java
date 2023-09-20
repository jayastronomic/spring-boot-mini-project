package com.example.library.controllers;

import com.example.library.models.Book;
import com.example.library.services.BookService;
import com.example.library.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user_books")
@Validated
public class BookController {
    /**
     * The BookService instance to handle book-related operations.
     */
    private final BookService bookService;

    /**
     * Constructs a new BookController with the specified BookService.
     *
     * @param bookService The BookService to be injected.
     */
    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    /**
     * Retrieves a list of all books in the library for the current user.
     *
     * @return ResponseEntity<APIResponse> A response containing the list of books.
     */
    @GetMapping
    public ResponseEntity<APIResponse> index(){
        return bookService.allBooks();
    }

    /**
     * Creates a new book in the library for the current user.
     *
     * @param book The Book object to be created.
     * @return ResponseEntity<APIResponse> A response indicating the result of the creation operation.
     */
    @PostMapping
    public ResponseEntity<APIResponse> create(@Valid @RequestBody Book book){
        return bookService.createBook(book);
    }

    /**
     * Retrieves information about a specific book by its unique identifier for the current user.
     *
     * @param id The unique identifier of the book.
     * @return ResponseEntity<APIResponse> A response containing the book's information.
     */
    @GetMapping("/user_books/{id}")
    public ResponseEntity<APIResponse> show(@PathVariable(value = "id") UUID id){
        return bookService.readBook(id);
    }

    /**
     * Updates an existing book in the library for the current user.
     *
     * @param book The Book object with updated information.
     * @return ResponseEntity<APIResponse> A response indicating the result of the update operation.
     */
    @PutMapping("/user_books/update")
    public ResponseEntity<APIResponse> update(@Valid @RequestBody Book book ){
        return bookService.updateBook(book);
    }

    /**
     * Deletes a book from the library by its unique identifier for the current user.
     *
     * @param id The unique identifier of the book to be deleted.
     * @return ResponseEntity<APIResponse> A response indicating the result of the deletion operation.
     */
    @DeleteMapping("/user_books/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable(value = "id") UUID id){
        return bookService.deleteBook(id);
    }
}

