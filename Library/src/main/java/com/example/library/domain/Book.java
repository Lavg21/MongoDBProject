package com.example.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private String _id;

    private String title;

    private String publicationYear;

    private String genre;

    private String authorId;

    private String categoryId;
}
