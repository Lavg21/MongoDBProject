package com.example.library.domain.dto;

import com.example.library.domain.entity.Author;
import com.example.library.domain.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookWithAuthorDTO {

    private Book book;
    private Author author;

}