package example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Application {
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("study");
    private static final EntityManager manager = factory.createEntityManager();
    private static final EntityTransaction transaction = manager.getTransaction();

    public static void main(String[] args) {


        try {
            transaction.begin();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }

    }
}
