package study.exam;

import com.Common;
import study.model.Ch04_AND_05.Member;
import study.model.Ch04_AND_05.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * 양방향 연관관계 예제
 */
public class Ch05_Relation_2 {

    public static void main(String[] args) {
        Common common = new Common();

        EntityManager manager = common.getManager();
        EntityTransaction transaction = manager.getTransaction();

        try{
            transaction.begin();
            //biDirection(manager);
            //save(manager);
            nonOwnerSave(manager);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }

        common.close();
    }

    /**
     * 양방향 연관관계를 이용한 Member찾기.
     */
    private static void biDirection(EntityManager manager) {
        Team team = manager.find(Team.class, "team");
        List<Member> members = team.getMembers();

        for( Member member : members) {
            System.out.println("member.username =" + member.getUserName());
        }
    }

    /**
     * 양방향 연관관계를 이용한 데이터 저장
     */
    private static void save(EntityManager manager) {
        Team team = new Team("team", "팀1");
        manager.persist(team); //팀1 저장.

        /**
         * 1. member1을 생성한다.
         * 2. 연관관계의 주인인 Member엔티티의 인스턴스 member1은 team1을 자기 팀으로 지정했다.
         * 3. Member엔티티인 member1을 영속화 한다.
         */
        Member member = new Member("member", "회원1");
        member.setTeam1(team);
        manager.persist(member);

        Member member12 = new Member("memver2", "회원2");
        member12.setTeam1(team);
        manager.persist(member12);
    }

    /**
     * 양방향 연관관계에서 연관관계의 주인이 아닌곳에서 저장을 했을 경우,
     */
    private static void nonOwnerSave(EntityManager manager) {
        Member member = new Member("member", "회원1");
        manager.persist(member);

        Member member12 = new Member("member12", "회원2");
        manager.persist(member12);

        /**
         * 연관관계의 주인도 아닌 Team이 감히 주인행세를 하면서 member를 추가해도
         *  member,2에는 Team에 대한 데이터가 들어가지 않는다.
         */
        Team team = new Team("team", "팀1");
        team.getMembers().add(member);
        team.getMembers().add(member12);

        manager.persist(team);
    }

}
