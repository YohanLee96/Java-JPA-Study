package study.model.Ch06;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter @Setter
@Entity
@Table(name = "COMPLEX_PK_PRODUCT")
@NoArgsConstructor
public class ComplexPkProduct {

    @Id
    @Column(name = "PRODUCT_ID")
    private String id;

    private String name;

    public ComplexPkProduct(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
