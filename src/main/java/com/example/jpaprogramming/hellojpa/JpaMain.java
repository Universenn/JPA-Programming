package com.example.jpaprogramming.hellojpa;

import com.example.jpaprogramming.hellojpa.domain2.Member;
import com.example.jpaprogramming.hellojpa.domain2.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
//            Order order = new Order();
//            order.addOrderItem(new OrderItem());
            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            Team team = new Team("team1");
            team.getMembers().add(member);
            em.persist(team);

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
