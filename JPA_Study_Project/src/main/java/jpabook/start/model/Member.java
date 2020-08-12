package jpabook.start.model;


import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Member {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME", nullable = false, length = 10)
    private String userName;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    //연관관계 설정
    public void setTeam(Team team) {
        this.team = team;
    }

}
