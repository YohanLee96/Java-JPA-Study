package study.model.Ch06;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "CH06_MANY_TO_ONE_MEMBER")
public class BiManyToOneMember {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID",
            insertable = false,
            updatable = false)
    private BiManyToOneTeam team;

    private String name;



    public BiManyToOneMember(String name) {
        this.name = name;
    }

    public BiManyToOneMember(String name, BiManyToOneTeam team) {
        this.name = name;
        this.team = team;
    }

    public void setTeam(BiManyToOneTeam team) {
        this.team = team;
    }
}
