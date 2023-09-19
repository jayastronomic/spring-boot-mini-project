package com.example.library.services;

import com.example.library.models.Book;
import com.example.library.repositories.BookRepository;
import com.example.library.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Logger;

@Service
public class BookService extends ApplicationService {
    Logger logger = Logger.getLogger(BookService.class.getName());
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public ResponseEntity<APIResponse> createBook(Book book){
        book.setUser(currentUser());
        return ResponseEntity
                .ok(new APIResponse(bookRepository.save(book)));
    }
}
