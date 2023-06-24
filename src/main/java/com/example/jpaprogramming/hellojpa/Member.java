package com.example.jpaprogramming.hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
//@Table(name = "MBR") // DB와 연동될 테이블 이름을 설정 할 수 있다.
public class Member {

    @Id
    private Long id;

    @Column(name = "name", nullable = false) // db 컬럼 이름
    private String username;

    private Integer age;

//    @Enumerated(EnumType.STRING) // enum 타입을 사용 하고 싶을 때
//    @Enumerated(EnumType.ORDINAL) // enum 타입을 사용 하고 싶을 때  //ORDINAL 은 순서를 나태내 준다.
    @Enumerated // enum 타입을 사용 하고 싶을 때
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP) // 날짜 시간 다포함
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifyDate;

    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    // Lob 매핑하는 타입이 문자면 CLob 매핑 나머지는 BLob 매핑
    @Lob // varcher을 넘어서는 값을 넣고싶으면 LOB을 사용 한다
    private String description;


    public Member() {
    }

    public Member(Long id, String username, RoleType roleType) {
        this.id = id;
        this.username = username;
        this.roleType = roleType;
    }
}
