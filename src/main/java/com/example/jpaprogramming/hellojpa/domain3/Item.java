package com.example.jpaprogramming.hellojpa.domain3;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// insert 쿼리도 한번에 조인 할 필요도 없어서 성능은 가장 잘 나온다
// 싱글 전략은 DTYPE 이 자동으로 생성된다. 구분할 방법이 없기 떄문에

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// 구현 전략은 insert 할 때는 문제가 없으니
// 조인 할 때 모든 테이블을(Union All) 다 뒤져야 하기 때문에 성능 이슈가 생긴다
@DiscriminatorColumn
// DTYPE 생성
public abstract class Item {

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
