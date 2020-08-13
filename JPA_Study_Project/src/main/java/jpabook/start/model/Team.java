package jpabook.start.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private String id;

    private String  name;

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }


}
