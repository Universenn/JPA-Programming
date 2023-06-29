package com.example.jpaprogramming.hellojpa.domain2;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;


    public Member(String username, Team team) {
        this.username = username;
        this.team = team;
        team.getMembers().add(this);
    }

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Member(String username) {
        this.username = username;
        team.getMembers().add(this);
    }



}
