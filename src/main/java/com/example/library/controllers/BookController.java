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
@RequestMapping("/api/v1/books")
@Validated
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<APIResponse> index(){
        return bookService.allbooks();
    }

    @PostMapping
    public ResponseEntity<APIResponse> create(@Valid @RequestBody Book book){
        return bookService.createBook(book);
    }

    @GetMapping("/user_book/{id}")
    public ResponseEntity<APIResponse> show(@PathVariable(value = "id") UUID id){
        return bookService.readBook(id);
    }

    @PutMapping("/user_book/update")
    public ResponseEntity<APIResponse> update(@Valid @RequestBody Book book ){
        return bookService.updateBook(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable(value = "id") UUID id){
        return bookService.deleteBook(id);
    }
}

