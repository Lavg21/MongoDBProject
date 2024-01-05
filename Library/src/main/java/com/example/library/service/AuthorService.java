package com.example.library.service;

import com.example.library.domain.entity.Author;
import com.example.library.domain.entity.Book;
import com.example.library.exception.EntityNotFoundException;
import com.example.library.exception.InvalidEmailException;
import com.example.library.exception.InvalidFieldException;
import com.example.library.exception.InvalidNumberException;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    @Autowired
    private final AuthorRepository authorRepository;
    @Autowired
    private final BookRepository bookRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.getAllAuthors();
    }

    public Author getAuthorById(String authorId) {
        Author author = authorRepository.getAuthorById(authorId);
        if (author == null) {
            throw new EntityNotFoundException("The author was not found!");
        }
        return author;
    }

//    public String createAuthor(Author author) {
//        validateAuthor(author);
//
//        if (!authorRepository.isEmailUnique(author.getEmail())) {
//            throw new InvalidEmailException("Email " + author.getEmail() + " is not unique!");
//        }
//
//        if (!authorRepository.isNameUnique(author.getName())) {
//            throw new InvalidFieldException("Name " + author.getName() + " is not unique!");
//        }
//
//        if (author.getAge() < 0) {
//            throw new InvalidNumberException("Age " + author.getAge() + " is not valid!");
//        }
//
//        authorRepository.createAuthor(author);
//        return "SUCCESS";
//    }

    public String createAuthor(Author author) {
        validateAuthor(author);

        if (!authorRepository.isEmailUnique(author.getEmail())) {
            throw new InvalidEmailException("Email " + author.getEmail() + " is not unique!");
        }

        if (!authorRepository.isNameUnique(author.getName())) {
            throw new InvalidFieldException("Name " + author.getName() + " is not unique!");
        }

        if (author.getAge() < 0) {
            throw new InvalidNumberException("Age " + author.getAge() + " is not valid!");
        }

        // Create the author
        Author createdAuthor = authorRepository.createAuthor(author);

        // Associate books with the author
        if (author.getBooks() != null) {
            for (Book book : author.getBooks()) {
                book.setAuthorId(createdAuthor.get_id());
                bookRepository.createBook(book);
            }
        }

        return "SUCCESS";
    }

    public String updateAuthor(String authorId, Author updatedAuthor) {
        validateAuthor(updatedAuthor);

        if (!authorRepository.isAuthorExists(authorId)) {
            throw new EntityNotFoundException("The author was not found!");
        }
        authorRepository.updateAuthor(authorId, updatedAuthor);
        return "SUCCESS";
    }

    public String deleteAuthor(String authorId) {
        if (!authorRepository.isAuthorExists(authorId)) {
            throw new EntityNotFoundException("The author was not found!");
        }
        authorRepository.deleteAuthor(authorId);
        return "SUCCESS";
    }

    private void validateAuthor(Author author) {
        if (StringUtils.isEmpty(author.getName())) {
            throw new InvalidFieldException("Author name cannot be empty!");
        }

        if (!isValidEmail(author.getEmail())) {
            throw new InvalidEmailException("The email address: " + author.getEmail() + " is not valid!");
        }

        if (author.getAge() < 0) {
            throw new InvalidNumberException("The age cannot be a negative number!");
        }
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }
}
