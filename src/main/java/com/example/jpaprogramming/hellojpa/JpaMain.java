package com.example.jpaprogramming.hellojpa;

import com.example.jpaprogramming.hellojpa.domain2.Member;
import com.example.jpaprogramming.hellojpa.domain2.Team;
import com.example.jpaprogramming.hellojpa.domian4.Child;
import com.example.jpaprogramming.hellojpa.domian4.Parent;

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


            Child child = new Child();
            child.setName("juwan");
            Child child2 = new Child();
            child2.setName("yeezi");
            Parent parent = new Parent();
            parent.setName("parent1");
            Parent parent2 = new Parent();
            parent2.setName("parent2");
            parent.addChild(child);
            parent.addChild(child2);
            em.persist(child);
            em.persist(child2);
            em.persist(parent2);

            em.persist(parent);

            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            em.remove(findParent);
//            findParent.getChildList().remove(0);

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
