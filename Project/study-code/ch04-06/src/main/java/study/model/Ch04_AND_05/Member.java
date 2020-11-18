package study.model.Ch04_AND_05;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "CH04_AND_CH05_MEMBER")
@NoArgsConstructor
public class Member {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME", nullable = false, length = 10)
    private String userName;

    @Column(name = "AGE")
    private int age;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Member(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    //연관관계 편의 메소드
    public void setTeam1(Team team) {
        if (team != null) {
            
            if(this.team != null) { //해당 분기를 안넣어주면 기존에 SET 했던 team에 Member정보가 아직 남아있다.
                this.team.getMembers().remove(this);
            }
            this.team = team;
            if(!team.getMembers().contains(this)) { //무한 루프에 빠지지 않도록 체크
                team.getMembers().add(this); //객체를 고려한 양방향 연관관계 설정.
            }
        }
    }
}
