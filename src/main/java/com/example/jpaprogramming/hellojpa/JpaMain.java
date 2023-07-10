package com.example.jpaprogramming.hellojpa;

import com.example.jpaprogramming.hellojpa.jpql.Member;
import com.example.jpaprogramming.hellojpa.jpql.MemberType;
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
//            Team team2 = new Team("Team2");
//            em.persist(team2);

//            for (int i = 0; i < 20; i++) {
//                Member member = new Member("A"+i, i,MemberType.USER, team1);
//                em.persist(member);
//            }
            Member member1 = new Member(25, MemberType.USER, team1);
            Member member2 = new Member("member2",26,MemberType.USER, team1);
            Member member3 = new Member(27, MemberType.USER, team1);
            Member member4 = new Member("      member4        ",28, MemberType.USER, team1);
            em.persist(member1);
            em.persist(member2);
            em.persist(member3);
            em.persist(member4);

//            Member member2 = new Member("Team2", 80, MemberType.ADMIN, team2);
//            em.persist(member2);

            em.flush();
            em.clear();

            System.out.println("-=--------------------------------------------------------------------------");
//            String query = "select m.username, 'HELLO', TRUE from Member m where m.type = :userType   ";
//            String query = "select coalesce(m.username, '이름 없는 회원') from Member m";
//            String query = "select nullif(m.username, 'member2') from Member m";
//            String query = "select trim(substring(m.username, 0, 3)) from Member m";
//            String query = "select trim(m.username) from Member m";
//            String query = "select length(m.username) from Member m";
//            String query = "select locate('e', m.username) from Member m";
            String query = "select size(t.member) from Team t";

            List<Integer> resultList = em.createQuery(query, Integer.class)
                    .getResultList();

            for (Integer s : resultList) {
                System.out.println("s : "+s);
            }
//            List<Object[]> resultList = em.createQuery(query)
////                    .setParameter("userType", com.example.jpaprogramming.hellojpa.jpql.MemberType.USER)
//                    .setParameter("userType", MemberType.USER)
//                    .getResultList();
//            for (Object[] objects : resultList) {
//                System.out.println("object : "+objects[0]);
//                System.out.println("object : "+objects[1]);
//                System.out.println("object : "+objects[2]);
//            }
//            List<Member> resultList = em.createQuery("select m from Member m where exists (select t from Team t where t.name = 'Team1')", Member.class).getResultList();
            System.out.println("-=--------------------------------------------------------------------------");

//            System.out.println("result.size = "+ resultList.size());
//            for (Member member : resultList) {
//                System.out.println("member : "+member);
//            }
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
