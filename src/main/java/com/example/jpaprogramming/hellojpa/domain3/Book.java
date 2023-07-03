package com.example.jpaprogramming.hellojpa.domain3;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
public class Book extends Item{
    private String author;
    private String isbn;
}
