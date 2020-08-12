package jpabook.start;

import javax.persistence.*;

public class JpaMain {
    //엔티티 매니저 팩토리 생성.(수행 이후, 닫아줘야함.)
    public static EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpabook");
    //엔티티매니저 팩토리에서 엔티티 매니저 생성.(수행 이후, 닫아줘야함.)
    public static EntityManager manager = factory.createEntityManager();
    //트랜잭션 획득
    public static EntityTransaction transaction = manager.getTransaction();

    public static void main(String[] args) {

        try{
            transaction.begin();

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            manager.close();
        }
        factory.close();
    }



}
