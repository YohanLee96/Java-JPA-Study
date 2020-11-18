package study.model.Ch06;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@NoArgsConstructor
@Table(name = "CH06_MANY_TO_MANY_MEMBER")
public class BiManyToManyMember {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String userName;

    /**
     * joinColumns : 현재방향인 회원 -> 상품 맵핑할 외래키 지정.
     * inverseJoinColumns : 반대방향인 상품 -> 회원 맵핑할 외래키 지정.
     */
    @ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT",
            joinColumns = @JoinColumn(name = "MEMBER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")
    )
    private List<BiManyToManyProduct> products = new ArrayList<>();

    public void addProduct(BiManyToManyProduct product) {
        this.products.add(product);
        product.getMembers().add(this);
    }
}
