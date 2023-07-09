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
//
            Member member1 = new Member("A", 25, team1);
            em.persist(member1);

//            em.createQuery("select o.address from Order o", Address.class).getResultList();
//            List<Object[]> resultList = em.createQuery("select distinct m.username, m.age from Member m").getResultList();
            List<MemberDto> resultList = em.createQuery("select new com.example.jpaprogramming.hellojpa.jpql.MemberDto(m.username, m.age) from Member m", MemberDto.class)
                    .getResultList();

            MemberDto memberDto = resultList.get(0);
            System.out.println("memberDto.username = "+ memberDto.getUsername());
            System.out.println("memberDto.age = "+ memberDto.getAge());


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
