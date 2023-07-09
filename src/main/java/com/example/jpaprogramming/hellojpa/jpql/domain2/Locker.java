package com.example.jpaprogramming.hellojpa.jpql.domain2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Locker {

    @Id @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;

    @Column(name = "LOCKERNAME")
    private String lockerName;

//    @OneToOne(mappedBy = "locker")
//    private Member member;

    public Locker(String lockerName) {
        this.lockerName = lockerName;
    }
}
