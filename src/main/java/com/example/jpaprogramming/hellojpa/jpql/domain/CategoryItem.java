package com.example.jpaprogramming.hellojpa.jpql.domain;

import com.example.jpaprogramming.hellojpa.jpql.domain.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class CategoryItem extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "CATEGORYITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
}
