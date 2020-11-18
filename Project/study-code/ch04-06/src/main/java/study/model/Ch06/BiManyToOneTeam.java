package study.model.Ch06;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "CH06_MANY_TO_ONE_TEAM")
@NoArgsConstructor
public class BiManyToOneTeam {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn(name = "TEAM_ID")
    private List<BiManyToOneMember> members  = new ArrayList<>();

    public BiManyToOneTeam(String name) {
        this.name = name;
    }

    public void addMember(BiManyToOneMember member) {
        if(!this.members.contains(member)) {
            this.members.add(member);
        }
    }



}
