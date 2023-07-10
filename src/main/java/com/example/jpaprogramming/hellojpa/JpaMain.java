package com.example.jpaprogramming.hellojpa;

import com.example.jpaprogramming.hellojpa.jpql.Member;
import com.example.jpaprogramming.hellojpa.jpql.Team;

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
            Team team1 = new Team("Team1");
            em.persist(team1);
            Team team2 = new Team("Team2");
            em.persist(team2);

            for (int i = 0; i < 3; i++) {
                Member member = new Member("A"+i, i, team1);
                em.persist(member);
            }

            Member member2 = new Member("Team2", 80, team2);
            em.persist(member2);

            em.flush();
            em.clear();

            System.out.println("-=--------------------------------------------------------------------------");
            String query = "select m from Member m join m.team t where t.name = 'Team1'";
            List<Member> resultList = em.createQuery(query, Member.class)
                    .getResultList();
            System.out.println("-=--------------------------------------------------------------------------");

//            System.out.println("result.size = "+ resultList.size());
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
