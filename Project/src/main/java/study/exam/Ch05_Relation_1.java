package study.exam;

import com.Common;
import study.model.Member;
import study.model.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * 단방향 연관관계 예제
 */
public class Ch05_Relation_1 {



    public static void main(String[] args) {
        Common common = new Common();

        EntityManager manager = common.getManager();
        EntityTransaction transaction = manager.getTransaction();

        try{
            transaction.begin();
            testSave(manager);
            searchObjectGraph(manager);
            queryLogicJoin(manager);
            updateRelation(manager);
            deleteRelation(manager);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        common.close();
    }

    /**
     * 연관관계 제거 로직
     */
    private static void deleteRelation(EntityManager manager) {
        Member member = manager.find(Member.class, "member1");
        member.setTeam(null);
    }


    /**
     * 연관관계를 통한 수정 로직
     */
    private static void updateRelation(EntityManager manager) {
        Team team2 = new Team("team2", "팀2");
        manager.persist(team2);

        Member member = manager.find(Member.class, "member1");
        member.setTeam(team2);
    }


    /**
     * JPQL을 이용한 조회.
     */
    private static void queryLogicJoin(EntityManager manager) {

        String jpql = "select m from Member m join m.team t where " +"t.name=:teamName";

        List<Member> resultList = manager.createQuery(jpql, Member.class)
                .setParameter("teamName", "개발팀")
                .getResultList();

        System.out.println("-------------");
        for(Member member : resultList) {
            System.out.println("userName=" + member.getUserName());
        }
        System.out.println("-------------");
    }

    /**
     * 객체 그래프탐색
     */
    private static void searchObjectGraph(EntityManager manager) {
        Member member = manager.find(Member.class, "member1");
        Team team = member.getTeam();   //객체 그래프 탐색
        System.out.println("팀 이름 : " + team.getName());
    }


    /**
     * 연관관계를 이용한 저장.
     */
    private static void testSave(EntityManager manager) {
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
