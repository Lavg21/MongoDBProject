package com.example.library.controller;

import com.example.library.domain.entity.Book;
import com.example.library.exception.EntityNotFoundException;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<Object> getBookById(@PathVariable String bookId) {
        try {
            Book book = bookService.getBookById(bookId);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(createErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(createErrorResponse("There was an error when retrieving the book!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/books")
    public ResponseEntity<Object> createBook(@RequestBody Book book) {
        try {
            String result = bookService.createBook(book);
            if ("SUCCESS".equals(result)) {
                return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(createErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<Object> updateBook(@RequestBody Book book, @PathVariable String bookId) {
        try {
            String result = bookService.updateBook(bookId, book);
            if ("SUCCESS".equals(result)) {
                return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(createErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Object> deleteBook(@PathVariable String bookId) {
        try {
            String result = bookService.deleteBook(bookId);
            if ("SUCCESS".equals(result)) {
                return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(createErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    private Map<String, Object> createErrorResponse(String errorMessage) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("Error message", errorMessage);
        return errorResponse;
    }

}
