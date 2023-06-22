package com.example.jpaprogramming.hellojpa;

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

            // JPQL 로 MemberList 조회 하기
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(5)
//                    .setMaxResults(8)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

//            Member findMember = em.find(Member.class, 1L);

            // 수정
            // 저장을 하지 않았는데 (em.persist(findMember);) 왜 저장이 될까?
            // JPA를 통해 엔티티를(Member.class) 가지고 오면 JPA가 엔티티를 관리해서 변경이 되었으면 알아서 업데이트 문을 날려준다.
//            findMember.setName("juwan");

            // 삭재
//            em.remove(findMember);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.clear();
        }
        emf.close();
    }
}
