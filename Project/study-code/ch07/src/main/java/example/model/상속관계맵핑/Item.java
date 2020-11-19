package example.model.상속관계맵핑;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //상속관계 맵핑 전략 지정.
@DiscriminatorColumn(name = "DTYPE") //엔티티 구분값을 저장할 컬럼명
public class Item {


    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;

    private int price;

}
