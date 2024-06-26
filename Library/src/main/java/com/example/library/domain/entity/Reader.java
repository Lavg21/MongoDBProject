package com.example.library.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reader {

    private String _id;

    private String firstname;

    private String lastname;

    private String email;

    private String phone;
}