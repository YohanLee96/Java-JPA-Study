package example.model.상속관계맵핑;

import example.model.상속관계맵핑.BaseEntity;

import javax.persistence.Entity;

@Entity
public class Seller extends BaseEntity {

    private String shopName;
}
