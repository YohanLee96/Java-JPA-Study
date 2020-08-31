package jpabook.start.study;

import jpabook.start.model.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


public class Ch04_EntityMapping {

    //엔티티 매니저 팩토리 생성.(수행 이후, 닫아줘야함.)
    public static EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpabook");
    //엔티티매니저 팩토리에서 엔티티 매니저 생성.(수행 이후, 닫아줘야함.)
    public static EntityManager manager = factory.createEntityManager();
    //트랜잭션 획득
    public static EntityTransaction transaction = manager.getTransaction();

    public static void main(String[] args) {

        try{
            transaction.begin();
            insert(manager, "johnxx1");
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
