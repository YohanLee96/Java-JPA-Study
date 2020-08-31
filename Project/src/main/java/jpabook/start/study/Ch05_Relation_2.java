package jpabook.start.study;

import jpabook.start.model.Member;
import jpabook.start.model.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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
            //biDirection();
            //save();
            nonOwnerSave();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }

        manager.close();
        factory.close();
    }

    /**
     * 양방향 연관관계를 이용한 Member찾기.
     */
    private static void biDirection() {
        Team team = manager.find(Team.class, "team1");
        List<Member> members = team.getMemberList();

        for( Member member : members) {
            System.out.println("member.username =" + member.getUserName());
        }
    }

    /**
     * 양방향 연관관계를 이용한 데이터 저장
     */
    private static void save() {
        Team team1 = new Team("team1", "팀1");
        manager.persist(team1); //팀1 저장.

        /**
         * 1. member1을 생성한다.
         * 2. 연관관계의 주인인 Member엔티티의 인스턴스 member1은 team1을 자기 팀으로 지정했다.
         * 3. Member엔티티인 member1을 영속화 한다.
         */
        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1);
        manager.persist(member1);

        Member member2 = new Member("memver2", "회원2");
        member2.setTeam(team1);
        manager.persist(member2);
    }

    /**
     * 양방향 연관관계에서 연관관계의 주인이 아닌곳에서 저장을 했을 경우,
     */
    private static void nonOwnerSave() {
        Member member1 = new Member("member1", "회원1");
        manager.persist(member1);

        Member member2 = new Member("member2", "회원2");
        manager.persist(member2);

        /**
         * 연관관계의 주인도 아닌 Team이 감히 주인행세를 하면서 member를 추가해도
         *  member1,2에는 Team에 대한 데이터가 들어가지 않는다.
         */
        Team team1 = new Team("team1", "팀1");
        team1.getMemberList().add(member1);
        team1.getMemberList().add(member2);

        manager.persist(team1);
    }

}
