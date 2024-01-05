package com.example.library.service;

import com.example.library.domain.dto.BookWithCategoryDTO;
import com.example.library.domain.entity.Book;
import com.example.library.domain.entity.Category;
import com.example.library.repository.BookRepository;
import com.example.library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {

    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    @Autowired
    public LibraryService(CategoryRepository categoryRepository, BookRepository bookRepository) {
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
    }

    public BookWithCategoryDTO getBookWithAuthor(String bookId) {
        Book book = bookRepository.getBookById(bookId);
        Category category = null;

        if (book != null) {
            // Retrieve the associated category using the categoryId stored in the book
            category = categoryRepository.getCategoryById(book.getCategoryId());
        }

        return new BookWithCategoryDTO(book, category);
    }
}
