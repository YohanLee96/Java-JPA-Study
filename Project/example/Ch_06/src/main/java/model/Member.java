package model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Table(name = "MEMBER")
@NoArgsConstructor
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String memberId;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "member"
    )
    private List<Orders> orders;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STREET")
    private String street;

    @Column(name = "ZIP_CODE")
    private String zipCode;



}
