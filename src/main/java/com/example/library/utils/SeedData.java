package com.example.library.utils;

import com.example.library.models.User;
import com.example.library.models.Book;
import com.example.library.repositories.UserRepository;
import com.example.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class SeedData implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public SeedData(@Lazy PasswordEncoder passwordEncoder,
                    UserRepository userRepository,
                    BookRepository bookRepository) {

        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create a user
        User user = new User();
        user.setUsername("Juju");
        user.setEmail("julian@example.com");
        user.setPassword(passwordEncoder.encode("password123"));
        userRepository.save(user);

        // Create 10 books for the user
        for (int i = 1; i <= 10; i++) {
            Book book = new Book();
            book.setTitle("Book " + i);
            book.setPages(200 + i * 10);
            book.setAuthor("Author " + i);
            book.setSummary("Summary of Book " + i);
            book.setUser(user);
            bookRepository.save(book);
        }
    }
}