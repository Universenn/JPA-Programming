package com.example.jpaprogramming.hellojpa;

import com.example.jpaprogramming.hellojpa.domain.Address;
import com.example.jpaprogramming.hellojpa.domain.Member;

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
            Address oldAddress = new Address("city", "street", "0000000000");
            Member member1 = new Member();
            member1.setName("juwan");
            member1.setHomeAddress(oldAddress);
            em.persist(member1);

            Address newAddress = new Address(oldAddress.getCity(), oldAddress.getStreet(), oldAddress.getZipcode());
            Member member2 = new Member();
            member2.setName("juwan");
            member2.setHomeAddress(newAddress);
            em.persist(member2);

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
