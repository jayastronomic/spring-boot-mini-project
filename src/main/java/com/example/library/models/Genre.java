package com.example.library.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "genres")
public class Genre extends ApplicationEntity<Genre> {

    @Override
    public void update(Genre payload) {

    }
}
