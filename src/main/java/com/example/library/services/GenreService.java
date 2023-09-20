package com.example.library.services;

import com.example.library.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {
    public final GenreRepository genreRepository;

    @Autowired
    public GenreService( GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    @c
}
