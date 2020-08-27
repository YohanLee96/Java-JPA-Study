package jpabook.start.study;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 양방향 연관관계 예제
 */
public class Ch05_Relation_2 {
    public static EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpabook");
    public static EntityManager manager = factory.createEntityManager();
    public static EntityTransaction transaction = manager.getTransaction();

    public static void main(String[] args) {
        try{
            transaction.begin();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }

        manager.close();
        factory.close();
    }


}
