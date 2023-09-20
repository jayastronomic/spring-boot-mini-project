package com.example.library.services;

import com.example.library.models.Book;
import com.example.library.repositories.BookRepository;
import com.example.library.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * The BookService class provides business logic for book-related operations.
 *
 * @Service Indicates that this class is a service component.
 */
@Service
public class BookService extends ApplicationService {
    /**
     * The logger instance for logging.
     */

    Logger logger = Logger.getLogger(BookService.class.getName());
    /**
     * The BookRepository instance for interacting with book data.
     */

    private final BookRepository bookRepository;

    /**
     * Constructs a new BookService with the specified BookRepository.
     *
     * @param bookRepository The BookRepository to be injected.
     */
    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    /**
     * Retrieves a list of all books associated with the current user.
     *
     * @return ResponseEntity<APIResponse> A response containing the list of user's books.
     */
    public ResponseEntity<APIResponse> allBooks(){
        return ResponseEntity.ok(new APIResponse(currentUser().getBooks()));
    }

    /**
     * Creates a new book and associates it with the current user.
     *
     * @param book The Book object to be created.
     * @return ResponseEntity<APIResponse> A response indicating the result of the creation operation.
     */
    public ResponseEntity<APIResponse> createBook(Book book){
        book.setUser(currentUser());
        return ResponseEntity
                .ok(new APIResponse(bookRepository.save(book)));
    }

    /**
     * Retrieves information about a specific book by its unique identifier.
     *
     * @param id The unique identifier of the book.
     * @return ResponseEntity<APIResponse> A response containing the book's information or an error if not found.
     */
    public ResponseEntity<APIResponse> readBook(UUID id){
      Book book = currentUser().findBookById(id);
      if(book != null) return ResponseEntity.ok(new APIResponse(book));
      return bookNotFound(id);
    }

    /**
     * Updates an existing book's information.
     *
     * @param bookUpdates The Book object with updated information.
     * @return ResponseEntity<APIResponse> A response indicating the result of the update operation or an error if not found.
     */
    public ResponseEntity<APIResponse> updateBook(Book bookUpdates){
        Book book = currentUser().findBookById(bookUpdates.getId());
        if(book != null){
            book.update(bookUpdates);
            return ResponseEntity.ok(new APIResponse(bookRepository.save(book)));
        }
        return bookNotFound(bookUpdates.getId());
    }

    /**
     * Deletes a book from the library by its unique identifier.
     *
     * @param id The unique identifier of the book to be deleted.
     * @return ResponseEntity<APIResponse> A response indicating the result of the deletion operation or an error if not found.
     */
    public ResponseEntity<APIResponse> deleteBook(UUID id){
        Book book = currentUser().findBookById(id);
        if(book != null) {
            bookRepository.deleteById(id);
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "Book successfully deleted");
            return ResponseEntity.ok(new APIResponse(response));
        }
        return bookNotFound(id);
    }

    /**
     * Generates a response for a book not found error.
     *
     * @param id The unique identifier of the book that was not found.
     * @return ResponseEntity<APIResponse> A response indicating the book was not found.
     */
    private ResponseEntity<APIResponse> bookNotFound(UUID id){
        Map<String,String> errors = new HashMap<>();
        errors.put("message", "Book not found with ID = " + id);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new APIResponse(errors));
    }
}
