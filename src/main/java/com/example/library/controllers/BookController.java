package com.example.library.controllers;

import com.example.library.models.Book;
import com.example.library.services.BookService;
import com.example.library.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
@Validated
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<APIResponse> create(@Valid @RequestBody Book book){
        return bookService.createBook(book);
    }

    @GetMapping("/user_book/{id}")
    public ResponseEntity<APIResponse> show(@PathVariable(value = "id") UUID id){
        return bookService.readBook(id);
    }

    @PostMapping("/user_book/update")
    public ResponseEntity<APIResponse> update(@Valid @RequestBody Book book ){
        return bookService.updateBook(book);
    }
}

