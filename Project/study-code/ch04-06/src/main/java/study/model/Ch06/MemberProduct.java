package study.model.Ch06;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Entity
@IdClass(MemberProductId.class)
@NoArgsConstructor
public class MemberProduct {

    @Id
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private ComplexPkMember member;

    @Id
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private ComplexPkProduct product;

    private int orderAmount;

    @Column(name = "ORDER_DATE")
    private Date orderDate;

    @Builder
    public MemberProduct(ComplexPkMember member, ComplexPkProduct product, int orderAmount, Date orderDate) {
        this.member = member;
        this.product = product;
        this.orderAmount = orderAmount;
        this.orderDate = orderDate;
    }
}
