package ShoppingMall.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private long id;

    @Column(name = "ITEM_ID")
    private long itemId;

    @Column(name = "ORDER_ID")
    private long orderId;

    private int orderPrice;
    private int count;

}
