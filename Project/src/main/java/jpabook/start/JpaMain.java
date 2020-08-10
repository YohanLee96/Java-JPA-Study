package jpabook.start;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        //엔티티 매니저 팩토리 생성.(수행 이후, 닫아줘야함.)
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpabook");
        //엔티티 매니저 생성.(수행 이후, 닫아줘야함.)
        EntityManager manager = factory.createEntityManager();
        //트랜잭션 획득
        EntityTransaction transaction = manager.getTransaction();

        try{
            transaction.begin();
           // insert(manager, "id1");
            update(manager, "id1");
            delete(manager, "id1");
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            manager.close();
        }
        factory.close();
    }

    public static void delete(EntityManager manager, String id) {
        Member member = manager.find(Member.class, id);
        manager.remove(member);
    }

    public static void update(EntityManager manager, String id) {
        Member member = manager.find(Member.class, id);
        member.setUserName("리요한");
        member.setAge(20);
    }

    public static void insert(EntityManager manager, String id) {
        Member member = new Member();
        member.setId(id);
        member.setUserName("이요한");
        member.setAge(25);

        manager.persist(member);

        member.setAge(30);

        //단일 조회
        Member findMember = manager.find(Member.class, id);
        System.out.println("findMember= " + findMember.getUserName() + ", age=" + findMember.getAge());

        //목록 조회
        List<Member> members = manager.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("members.size =" + members.size());

       // manager.remove(member);
    }
}
