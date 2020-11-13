package actual_exam.ch05_example;

import actual_exam.ch05_example.model.*;
import com.Common;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.Date;

public class Exam {
    private static final Long id = 1L;

    public static void main(String[] args) {
        Common common  = new Common();


        EntityManager manager = common.getManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();
            getShopMemberAsOrder(manager);
            getItemAsOrderItemAsOrder(manager);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        
        common.close();
    }


    public static void getShopMemberAsOrder(EntityManager manager) {
        Order order = manager.find(Order.class, id);
        ShopMember member = order.getShopMember();

        System.out.println("회원의 이름 : " + member.getName());
    }

    public static void getItemAsOrderItemAsOrder(EntityManager manager) {
        Order order = manager.find(Order.class, id);
        if(!order.getOrderItems().isEmpty()) {
            OrderItem orderItem = order.getOrderItems().get(0);
            Item item = orderItem.getItem();
            System.out.println("상품의 이름 : " + item.getName());
        }
    }

    public static ShopMember getBuildShopMember() {
        return ShopMember.builder()
                .id(id)
                .name("이요한")
                .city("수원시")
                .street("수원로")
                .zipCode("345-2")
                .build();
    }

    //회원의 주문
    public static Order getBuildOrder(ShopMember shopMember) {
        return Order.builder()
                .id(id)
                .shopMember(shopMember)
                .orderDate(new Date())
                .orderStatus(OrderStatus.ORDER)
                .build();
    }

    //회원의 주문한 주문상품
    public static OrderItem getBuildOrderItem(Order order) {
        return OrderItem.builder()
                .id(id)
                .item(getBuildItem())
                .count(2)
                .orderPrice(53000)
                .order(order)
                .build();
    }

    //상품
    public static Item getBuildItem() {
        return Item.builder()
                .id(id)
                .name("텀블러")
                .price(43000)
                .stockQuantity(34)
                .build();
    }
}
