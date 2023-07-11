package com.example.jpaprogramming.hellojpa.jpql;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MemberJpql")
@NamedQuery(
        name = "MemberJpql.findByUsername",
        query = "select m from MemberJpql m where m.username =:username "
)
public class MemberJpql {
    @Id @GeneratedValue
    private Long id;
    private String username;
    private int age;

    @Enumerated(EnumType.STRING)
    private MemberType type;

    public void changeTeam(TeamJpql team){
        this.team = team;
        team.getMemberJpql().add(this);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private TeamJpql team;


    @Override
    public String toString() {
        return "MemberJpql{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }

    public MemberJpql(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public MemberJpql(String username, int age, MemberType type) {
        this.username = username;
        this.age = age;
        this.type = type;
    }

    public MemberJpql(String username, int age, TeamJpql team) {
        this.username = username;
        this.age = age;
        this.team = team;
    }

    public MemberJpql(int age, MemberType type, TeamJpql team) {
        this.age = age;
        this.type = type;
        this.team = team;
    }

    public MemberJpql(String username, int age, MemberType type, TeamJpql team) {
        this.username = username;
        this.age = age;
        this.type = type;
        this.team = team;
    }
}
