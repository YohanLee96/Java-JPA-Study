package study.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@NoArgsConstructor
@Table(name = "TEAM")
public class Team {

    @Id
    @Column(name = "TEAM_ID")
    private String id;

    private String  name;

    /**
     * 연관관계의 주인이 아니니, mappedBy를 써서
     * Member Entity에 team이라는 필드가 주인이라는것을 알려준다.
     */
    @OneToMany(mappedBy = "team")
    private List<Member> memberList;

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
        this.memberList = new ArrayList<>();
    }


}
