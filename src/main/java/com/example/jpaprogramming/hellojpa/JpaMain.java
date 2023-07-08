package com.example.jpaprogramming.hellojpa;

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
//            Address oldAddress = new Address("city", "street", "0000000000");
//            Member member1 = new Member();
//            member1.setName("juwan");
//            member1.setHomeAddress(oldAddress);
//            member1.getFavoriteFoods().add("치킨");
//            member1.getFavoriteFoods().add("족발");
//            member1.getAddressesHistory().add(new AddressEntity("new city1", "street", "0000000000"));
//            member1.getAddressesHistory().add(new AddressEntity("new city3", "street", "0000000000"));
//            member1.getAddressesHistory().add(new AddressEntity("new city4", "street", "0000000000"));
//            em.persist(member1);
//
//            em.flush();
//            em.clear();
//
//            Member findMember = em.find(Member.class, member1.getId());
//
//            // 수정
//            // 치킨 -> 피자 수정(삭제 해야 함)
//            // String 은 수정이 안되기 때문에 삭제 후 생성해야한다
//            findMember.getFavoriteFoods().remove("치킨");
//            findMember.getFavoriteFoods().add("피자");
//
//            // net city -> old city 수정
//            findMember.getAddressesHistory().remove(new Address("new city1", "street", "0000000000"));
//            findMember.getAddressesHistory().add(new Address("old city", "street", "0000000000"));
//
//            // 컬렉션은 지연로딩
//            findMember.setHomeAddress(new Address("inchen", "street", "0000000000"));
//            Set<String> favoriteFoods = findMember.getFavoriteFoods();
//            for (String favoriteFood : favoriteFoods) {
//                System.out.println("favoriteFood : "+favoriteFood);
//            }
//
//            List<Address> addressesHistory = findMember.getAddressesHistory();
//            for (Address address : addressesHistory) {
//                System.out.println("address : "+address.getCity());
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
