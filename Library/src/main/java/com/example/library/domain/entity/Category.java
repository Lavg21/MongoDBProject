package com.example.library.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    private String _id;

    private String name;

    private String description;

    private Integer numberOfBooks;

    private boolean isActive;

    private String createdBy;
}
