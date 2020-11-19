package example.model.상속관계맵핑;

import example.model.상속관계맵핑.Item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
public class Movie extends Item {

    private String director;

    private String actor;
}
