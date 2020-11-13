package study.exam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.lang.reflect.Method;

public class Common {

    private final EntityManagerFactory factory;
    private final EntityManager manager;
    private final EntityTransaction transaction;

    public Common() {
        factory = Persistence.createEntityManagerFactory("study");
        manager = factory.createEntityManager();
        transaction = manager.getTransaction();
    }

    public  EntityManager getManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("study");
        return factory.createEntityManager();
    }

    public void close() {
        if(this.manager != null) {
            this.manager.close();
        }

        if(this.factory != null) {
            this.factory.close();
        }
    }
    





}
