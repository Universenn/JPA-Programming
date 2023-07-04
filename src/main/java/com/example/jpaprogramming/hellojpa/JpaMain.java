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
            Team team = new Team("Team1");
            em.persist(team);

            Team team2 = new Team("Team2");
            em.persist(team2);

            Member member1 = new Member();
            member1.setUsername("juwan");
            member1.setTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("juwan2");
            member2.setTeam(team2);
            em.persist(member2);

            em.flush();
            em.clear();

//            Member findMember1 = em.find(Member.class, member1.getId());

//            List<Member> members = em.createQuery("select m from Member m", Member.class)
//                    .getResultList();

            // join fetch 를 이용해 N+1문제 해결
            List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class)
                    .getResultList();

            tx.commit();
        } catch (Exception e) {
            System.out.println("rollback");
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.clear();
        }
        emf.close();
    }

    private static void logic(Member findMember1, Member findMember2) {
        System.out.println("findMember1 == findMember2 : "+(findMember1.getClass() == findMember2.getClass()));
        System.out.println("findMember1 instanceof Member  : "+(findMember1 instanceof Member));
        System.out.println("findMember2 instanceof Member  : "+(findMember2 instanceof Member));
    }

}
