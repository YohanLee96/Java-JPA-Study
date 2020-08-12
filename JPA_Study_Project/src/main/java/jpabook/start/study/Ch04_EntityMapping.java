package jpabook.start.study;

import jpabook.start.model.Member;

import javax.persistence.EntityManager;
import java.util.List;

public class Ch04_EntityMapping {

    public  void delete(EntityManager manager, String id) {
        Member member = manager.find(Member.class, id);
        manager.remove(member);
    }

    public  void update(EntityManager manager, String id) {
        Member member = manager.find(Member.class, id);
        member.setUserName("리요한");
        member.setAge(20);
    }

    public  void insert(EntityManager manager, String id) {
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
