package com.example.library.domain.dto;

import com.example.library.domain.entity.Book;
import com.example.library.domain.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookWithCategoryDTO {

    private Book book;

    private Category category;
}