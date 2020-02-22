package com.example.mybatisdemo.jpa;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Repository
public class UserJpa {
    public void saveUser(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("user_entity");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        UserEntity userEntity = new UserEntity();
        userEntity.setAge(54);
        userEntity.setName("王二小");
        userEntity.setSex("男");

        em.persist(userEntity);

        em.getTransaction().commit();

        em.close();
        emf.close();

        System.out.println("All job done.");
    }

    public UserEntity getUser(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("user_entity");
        EntityManager em = emf.createEntityManager();

        UserEntity userEntity = em.find(UserEntity.class, id);

        System.out.println(" id = " + userEntity.getId());
        System.out.println(" Name = " + userEntity.getName());
        System.out.println(" Age = " + userEntity.getAge());

        return userEntity;
    }
}
