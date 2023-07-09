package com.example.jpaprogramming.hellojpa.jpql.domain2;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Product {

    @Id @GeneratedValue
    @Column(name = "PRODUCT_ID")
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "products")
    private List<Member> member = new ArrayList<>();
}
