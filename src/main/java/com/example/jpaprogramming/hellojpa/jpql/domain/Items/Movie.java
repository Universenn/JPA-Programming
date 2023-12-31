package com.example.jpaprogramming.hellojpa.jpql.domain.Items;

import com.example.jpaprogramming.hellojpa.jpql.domain.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Movie extends Item {
    private String director;
    private String actor;
}
