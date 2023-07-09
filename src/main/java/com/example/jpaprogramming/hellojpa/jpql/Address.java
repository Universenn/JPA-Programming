package com.example.jpaprogramming.hellojpa.jpql;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    @Column(length = 10)
    private String city;

    @Column(length = 10)
    private String street;

    @Column(length = 10)
    private String zipcode;
}
