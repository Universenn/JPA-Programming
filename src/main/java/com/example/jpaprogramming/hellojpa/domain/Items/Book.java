package com.example.jpaprogramming.hellojpa.domain.Items;

import com.example.jpaprogramming.hellojpa.domain.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Book extends Item {
    private String artist;
    private String isbn;
}
