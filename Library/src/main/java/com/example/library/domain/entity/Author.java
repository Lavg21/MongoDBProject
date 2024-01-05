package com.example.library.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    private String _id;

    private String name;

    private String email;

    private String genre;

    private String country;

    private Integer age;

    private List<Book> books;

    public Author(String _id, String name, String email, String genre, String country, Integer age) {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.genre = genre;
        this.country = country;
        this.age = age;
    }
}