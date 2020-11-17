package study.model.Ch06;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "CH06_MANY_TO_MANY_PRODUCT")
@NoArgsConstructor
public class BiManyToManyProduct {
    @Id
    @Column(name = "PRODUCT_ID")
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "products")
    private List<BiManyToManyMember> members = new ArrayList<>();
}
