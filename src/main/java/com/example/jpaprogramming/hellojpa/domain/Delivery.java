package com.example.jpaprogramming.hellojpa.domain;

import com.example.jpaprogramming.hellojpa.domain.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus deliveryStatus;
}
