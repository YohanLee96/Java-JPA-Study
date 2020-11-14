package actual_exam.ch05_example.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class ShopMember {

    @Id
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    private String city;

    private String street;

    private String zipCode;

    @OneToMany(mappedBy = "shopMember")
    private List<Order> orders = new ArrayList<>();

    @Builder
    public ShopMember(Long id, String name, String city, String street, String zipCode, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.orders = orders;
    }
}
