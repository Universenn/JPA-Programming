package com.example.jpaprogramming.hellojpa.jpql.domain;

import com.example.jpaprogramming.hellojpa.jpql.domain.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;

    @Embedded
    private Address address;

    // 기간 Period
    @Embedded
    private Period period;



    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
