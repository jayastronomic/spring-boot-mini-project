package com.example.library.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "genres")
public class Genre extends ApplicationEntity<Genre> {
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Name can not be blank")
    private String name;


    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne
    private User user;

    @ManyToMany
    private List<Book> books;

    @Override
    public void update(Genre payload) {
    }
}
