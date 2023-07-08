package com.example.jpaprogramming.hellojpa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "ADDRESS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressEntity {

    @Id @GeneratedValue
    private Long id;

    private Address address;

    public AddressEntity(String city, String street, String zipCode) {
        this.address = new Address(city, street, zipCode);
    }

}
