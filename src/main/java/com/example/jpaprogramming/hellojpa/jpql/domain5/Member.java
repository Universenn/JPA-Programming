package com.example.jpaprogramming.hellojpa.jpql.domain5;

import com.example.jpaprogramming.hellojpa.jpql.domain.Order;
import com.example.jpaprogramming.hellojpa.jpql.domain.Period;
import com.example.jpaprogramming.hellojpa.jpql.domain.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;

    // 기간 Period
    @Embedded
    private Period period;

    // 주소 Address
    @Embedded
    private Address homeAddress;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns =
        @JoinColumn(name = "MEMBER_ID")
    )
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addressesHistory = new ArrayList<>();


    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
