package com.example.library;

import com.example.library.domain.dto.BookWithAuthorDTO;
import com.example.library.service.LibraryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude={MongoAutoConfiguration.class})
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(LibraryService libraryService) {
        return args -> {
            String bookId = "6596f930d6f16b1279ea7a46";

            BookWithAuthorDTO bookWithAuthor = libraryService.getBookWithAuthor(bookId);

            if (bookWithAuthor.getBook() != null) {
                System.out.println("Book Title: " + bookWithAuthor.getBook().getTitle());

                if (bookWithAuthor.getAuthor() != null) {
                    System.out.println("Author Name: " + bookWithAuthor.getAuthor().getName());
                } else {
                    System.out.println("No author found for the book.");
                }
            } else {
                System.out.println("Book not found with ID: " + bookId);
            }
        };
    }

}
