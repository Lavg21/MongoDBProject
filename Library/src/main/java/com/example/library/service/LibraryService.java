package com.example.library.service;

import com.example.library.domain.dto.BookWithAuthorDTO;
import com.example.library.domain.entity.Author;
import com.example.library.domain.entity.Book;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public LibraryService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public BookWithAuthorDTO getBookWithAuthor(String bookId) {
        Book book = bookRepository.getBookById(bookId);
        Author author = null;

        if (book != null) {
            // Retrieve the associated author using the authorId stored in the book
            author = authorRepository.getAuthorById(book.getAuthorId());
        }

        return new BookWithAuthorDTO(book, author);
    }
}
