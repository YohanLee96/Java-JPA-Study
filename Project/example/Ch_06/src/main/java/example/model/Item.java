package example.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "ITEM")
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "item"
    )
    private List<Category> categories = new ArrayList<>();

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "STOCK_QUANTITY")
    private int stockQuantity;

    @Builder
    public Item(List<Category> categories, String name, int price, int stockQuantity) {
        this.categories = categories;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
