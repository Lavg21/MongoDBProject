package com.example.library.controller;

import com.example.library.domain.Author;
import com.example.library.exception.AuthorNotFoundException;
import com.example.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping("/authors/{authorId}")
    public ResponseEntity<Object> getAuthorById(@PathVariable String authorId) {
        try {
            Author author = authorService.getAuthorById(authorId);
            return new ResponseEntity<>(author, HttpStatus.OK);
        } catch (AuthorNotFoundException e) {
            return new ResponseEntity<>(createErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(createErrorResponse("There was an error when retrieving the author!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/authors")
    public ResponseEntity<Object> createAuthor(@RequestBody Author author) {
        try {
            String result = authorService.createAuthor(author);
            if ("SUCCESS".equals(result)) {
                return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(createErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/authors/{authorId}")
    public ResponseEntity<Object> updateAuthor(@RequestBody Author author, @PathVariable String authorId) {
        try {
            String result = authorService.updateAuthor(authorId, author);
            if ("SUCCESS".equals(result)) {
                return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(createErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/authors/{authorId}")
    public ResponseEntity<Object> deleteAuthor(@PathVariable String authorId) {
        try {
            String result = authorService.deleteAuthor(authorId);
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
