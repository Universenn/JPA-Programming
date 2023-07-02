package com.example.jpaprogramming.hellojpa.domain2;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Team {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    //(mappedBy = "team") : Member team 이라는 변수와 연결되어 있다. mappedBy 는 읽기 만 가능하다 주인은 team 이다
    // 조회는 가능하지만 업데이트할 떄는 주인(Member.getTeam())만 참조한다.
//    @OneToMany(mappedBy = "team")
    @OneToMany
    @JoinColumn(name = "TEAM_ID")
    private List<Member> members = new ArrayList <>();
    // = new ArrayList <>(); 쓰는 이유 add 할 때 null 이 발생하지 않아서
    public Team(String name) {
        this.name = name;
    }

}
