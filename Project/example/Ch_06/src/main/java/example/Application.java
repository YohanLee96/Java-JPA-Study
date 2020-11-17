package example;

import example.model.Category;
import example.model.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Application {

    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("study");;
    private static final EntityManager manager =  factory.createEntityManager();
    private static final EntityTransaction transaction = manager.getTransaction();

    public static void main(String[] args) {
        // TODO: 2020-11-18  1. 상품 엔티티 빌드
        Item nike = NIKE_PADDING();
        
        // TODO: 2020-11-18 2. 카테고리 엔티티 빌드
        // TODO: 2020-11-18 회원 엔티티 빌드
        // TODO: 2020-11-17 상품을 주문할 때 배송 정보를 입력할 수 있다.
        // TODO: 2020-11-17 상품을 카테고리로 구분할 수 있다.

        try {
            transaction.begin();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }

        manager.close();
        factory.close();

    }

    public static Item NIKE_PADDING() {
        return Item.builder()
                .name("나이키 패딩")
                .price(135000)
                .stockQuantity(23)
                .build();
    }
}
