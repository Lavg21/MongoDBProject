package com.example.library.controller;

import com.example.library.domain.Author;
import com.example.library.domain.Book;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LibraryController {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookRepository.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorRepository.getAllAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping("/authors/{authorId}")
    public ResponseEntity<Author> getAuthorById(@PathVariable String authorId) {
        Author author = authorRepository.getAuthorById(authorId);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PostMapping("/authors")
    public ResponseEntity<String> getAuthor(@RequestBody Author author) {
        authorRepository.createAuthor(author);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @PutMapping("/authors/{authorId}")
    public ResponseEntity<String> updateAuthor(@RequestBody Author author, @PathVariable String authorId) {
        authorRepository.updateAuthor(authorId, author);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @DeleteMapping("/authors/{authorId}")
    public ResponseEntity<String> deleteAuthor(@PathVariable String authorId) {
        authorRepository.deleteAuthor(authorId);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
}
