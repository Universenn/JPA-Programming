package com.example.jpaprogramming.hellojpa.domain3;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
// 기본은 default 값은 : SINGLE_TABLE
@DiscriminatorColumn
// DTYPE 생성
public class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

    public Item() {
    }

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
