package study.exam;

import com.Common;
import study.model.Ch06.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Date;
import java.util.List;

public class Ch06_Relation_1 {
    public static void main(String[] args) {
        Common common = new Common();

        EntityManager manager = common.getManager();
        EntityTransaction transaction = manager.getTransaction();

        try{
            transaction.begin();
            //testSave(manager);
            //testSave2(manager);
            //nAndnSave(manager);
            nAndnComplexPkSave(manager);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        nAndnComplexPkFind(manager);
        //nAndnFind(manager);
        //nAndnInverseFind(manager);
        common.close();



    }

    // 복합키를 통한 다대다 연관관계 조회.
    private static void nAndnComplexPkFind(EntityManager manager) {
        MemberProductId memberProductId = new MemberProductId();
        memberProductId.setMember(1L);
        memberProductId.setProduct("product1");

        //복합키를 사용할 경우, 조회할때도 식별자클래스를 통해 조회해야한다.
        MemberProduct memberProduct = manager.find(MemberProduct.class, memberProductId);

        ComplexPkMember member = memberProduct.getMember();
        ComplexPkProduct product = memberProduct.getProduct();

        System.out.println("member = " + member.getName());
        System.out.println("product = " + product.getName());
        System.out.println("orderAmount = " + memberProduct.getOrderAmount());


    }

    private static void nAndnComplexPkSave(EntityManager manager) {
        ComplexPkMember yohan = new ComplexPkMember(1L, "이요한");
        ComplexPkProduct benz = new ComplexPkProduct("product1", "벤츠 GLE");
        MemberProduct memberProduct = MemberProduct.builder()
                .member(yohan)
                .product(benz)
                .orderAmount(2)
                .orderDate(new Date())
                .build();

        manager.persist(memberProduct);
    }

    //다대다관계가 양방향 연관관계 ->  역방향으로도 조회 가능.
    private static void nAndnInverseFind(EntityManager manager) {
        BiManyToManyProduct product = manager.find(BiManyToManyProduct.class, 1L);
        List<BiManyToManyMember> members = product.getMembers();

        for(BiManyToManyMember member : members) {
            System.out.println("member.name = " + member.getUserName());
        }
    }

    //다대다 양방향 연관관계 조회
    private static void nAndnFind(EntityManager manager) {
        BiManyToManyMember member = manager.find(BiManyToManyMember.class, "member1");
        List<BiManyToManyProduct> products = member.getProducts();

        for (BiManyToManyProduct product : products) {
            System.out.println("product.name =" + product.getName());
        }
    }

    //다대다 양방향 저장.
    private static void nAndnSave(EntityManager manager) {
        //product Insert
        //member Insert
        //연결테이블에 각각의 조인컬럼값 Insert

        BiManyToManyMember member = new BiManyToManyMember();
        member.setId("member1");
        member.setUserName("회원1");

        BiManyToManyProduct product = new BiManyToManyProduct();
        product.setId(1L);
        product.setName("상품A");

        member.addProduct(product);


        manager.persist(member);
        manager.persist(product);



    }

    private static void testSave2(EntityManager manager) {
        BiManyToOneTeam team = new BiManyToOneTeam("team1");
        BiManyToOneMember member1 = new BiManyToOneMember("member1");
        BiManyToOneMember biManyToOneMember = new BiManyToOneMember("biManyToOneMember");

        member1.setTeam(team);
        biManyToOneMember.setTeam(team);

        team.addMember(member1);
        team.addMember(biManyToOneMember);

        manager.persist(team);
        manager.persist(member1);
        manager.persist(biManyToOneMember);
    }

    /**
     * 일대다 단방향일 경우, 3번의 INSERT로 가능할 로직이 UPDATE쿼리를 포함해서
     * 총 5개의 쿼리가 실행됨.
     */
    private static void testSave(EntityManager manager) {
        BiManyToOneMember member1 = new BiManyToOneMember("member1");
        BiManyToOneMember biManyToOneMember = new BiManyToOneMember("biManyToOneMember");

        BiManyToOneTeam team = new BiManyToOneTeam("team1");

        team.getMembers().add(member1);
        team.getMembers().add(biManyToOneMember);

        manager.persist(member1); //member1 Insert
        manager.persist(biManyToOneMember); //biManyToOneMember Insert
        manager.persist(team); // team Insert, member1 Update, biManyToOneMember Update

    }


}
