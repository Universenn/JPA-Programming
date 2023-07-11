package com.example.jpaprogramming.hellojpa;

import com.example.jpaprogramming.hellojpa.jpql.MemberJpql;
import com.example.jpaprogramming.hellojpa.jpql.MemberType;
import com.example.jpaprogramming.hellojpa.jpql.TeamJpql;

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
            TeamJpql team1 = new TeamJpql("Team1");
            em.persist(team1);
            TeamJpql team2 = new TeamJpql("Team2");
            em.persist(team2);
            TeamJpql team3 = new TeamJpql("Team3");
            em.persist(team3);

            MemberJpql member1 = new MemberJpql("member1",25, MemberType.USER, team1);
            MemberJpql member2 = new MemberJpql("member2",26,MemberType.USER, team1);
            MemberJpql member3 = new MemberJpql("member3",27, MemberType.USER, team2);
            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

            em.flush();
            em.clear();

            List<MemberJpql> resultList = em.createNamedQuery("MemberJpql.findByUsername", MemberJpql.class)
                    .setParameter("username", "member2")
                    .getResultList();

            for (MemberJpql memberJpql : resultList) {
                System.out.println("member = "+memberJpql);
            }

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

}
