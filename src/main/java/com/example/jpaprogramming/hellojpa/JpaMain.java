package com.example.jpaprogramming.hellojpa;

import com.example.jpaprogramming.hellojpa.jpql.Address;
import com.example.jpaprogramming.hellojpa.jpql.Member;
import com.example.jpaprogramming.hellojpa.jpql.MemberDto;
import com.example.jpaprogramming.hellojpa.jpql.Team;

import javax.persistence.*;
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
            Team team1 = new Team("Team1");
            em.persist(team1);

            for (int i = 0; i < 100; i++) {
                Member member = new Member("A"+i, 100-i, team1);
                em.persist(member);
            }

            em.flush();
            em.clear();

            System.out.println("-=--------------------------------------------------------------------------");
            List<Member> resultList = em.createQuery("select m from Member m order by m.age desc", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();
            System.out.println("-=--------------------------------------------------------------------------");

            System.out.println("result.size = "+ resultList.size());
            for (Member member : resultList) {
                System.out.println("member : "+member);
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
