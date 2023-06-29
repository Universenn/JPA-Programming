package com.example.jpaprogramming.hellojpa;

import com.example.jpaprogramming.hellojpa.domain2.Member;
import com.example.jpaprogramming.hellojpa.domain2.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // EntityManagerFactory 웹 서버가 올라 올 때 하나만 생성 된다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // EntityManager 는 고객에 요청이 올 때 마다 생성 된다.
        EntityManager em = emf.createEntityManager();

        // JPA에 모든 데이터 변경은 트랜잭션 안에서 실행 해야 한다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team("TeamA");
            em.persist(team);

            Member member1 = new Member("Member1",team); // 연관관계 주인은 후보를 가지고 있어야 한다.
            em.persist(member1);

            Member member2 = new Member("Member2", team);
            Member member3 = new Member("Member3", team);
            Member member4 = new Member("Member4", team);
            Member member5 = new Member("Member5", team);
            em.persist(member2);
            em.persist(member3);
            em.persist(member4);
            em.persist(member5);

//            team.getMembers().add(member1);
//            team.getMembers().add(member2);
//            team.getMembers().add(member3);
//            team.getMembers().add(member4);
//            team.getMembers().add(member5);

//            em.flush();
//            em.clear(); //영속성 컨텍스트를 끝낸다. 1차 캐시에 아무것도 없는 상태가 된다

            // 여기서 부터 영속성 컨택스트에서 가져온게 아니라 DB 에서 가져 온다.
            Team findMember = em.find(Team.class, team.getId());
            List<Member> members = findMember.getMembers();

            for (Member m : members) {
                System.out.println("m = "+ m.getUsername());
            }
            

            tx.commit();
        } catch (Exception e) {
            System.out.println("rollback");
            tx.rollback();
        }finally {
            em.clear();
        }
        emf.close();
    }
}
