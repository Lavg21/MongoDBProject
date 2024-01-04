package com.example.library.service;

import com.example.library.domain.Book;
import com.example.library.exception.EntityNotFoundException;
import com.example.library.exception.InvalidFieldException;
import com.example.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    @Autowired
    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public Book getBookById(String bookId) {
        Book book = bookRepository.getBookById(bookId);
        if (book == null) {
            throw new EntityNotFoundException("The book was not found!");
        }
        return book;
    }

    public String createBook(Book book) {
        validateBook(book);

        bookRepository.createBook(book);
        return "SUCCESS";
    }

    public String updateBook(String bookId, Book updatedBook) {
        validateBook(updatedBook);

        if (!bookRepository.isBookExists(bookId)) {
            throw new EntityNotFoundException("The book was not found!");
        }
        bookRepository.updateBook(bookId, updatedBook);
        return "SUCCESS";
    }

    public String deleteBook(String bookId) {
        if (!bookRepository.isBookExists(bookId)) {
            throw new EntityNotFoundException("The book was not found!");
        }
        bookRepository.deleteBook(bookId);
        return "SUCCESS";
    }

    private void validateBook(Book book) {
        if (StringUtils.isEmpty(book.getTitle()) || StringUtils.isEmpty(book.getGenre())
                || StringUtils.isEmpty(book.getPublicationYear()) || StringUtils.isEmpty(book.getAuthorId())
                || StringUtils.isEmpty(book.getCategoryId())) {
            throw new InvalidFieldException("Complete all the fields!");
        }

    }
}
