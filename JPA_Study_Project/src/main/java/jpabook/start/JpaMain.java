package jpabook.start;

import jpabook.start.model.Member;
import jpabook.start.model.Team;

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
            testSave();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            manager.close();
        }
        factory.close();
    }


    public static void testSave() {
        //팀 저장
        Team team1 = new Team("team1", "개발팀");
        manager.persist(team1); //영속성 처리

        //이요한 회원 저장.
        Member member1 = new Member("member1", "이요한");
        member1.setTeam(team1);
        manager.persist(member1);

        Member member2 = new Member("member2", "홍길동");
        member2.setTeam(team1);
        manager.persist(member2);

    }



}
