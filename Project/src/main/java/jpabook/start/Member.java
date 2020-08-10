package jpabook.start;


import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Getter @Setter
@Entity
@Table(name = "member")
public class Member {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String userName;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    //date+time의 timestamp 타입
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    //CLOB, BLOB 타입
    @Lob
    private String description;


}
