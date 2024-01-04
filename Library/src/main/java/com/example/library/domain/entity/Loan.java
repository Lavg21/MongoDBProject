package com.example.library.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Loan {

    private String _id;

    private String bookId;

    private String readerId;

    private String loanDate;

    private String dueDate;

    private String returnDate;
}