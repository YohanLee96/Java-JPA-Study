package jpabook.start;


import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Getter @Setter
@Entity
@Table(name = "member", uniqueConstraints = {   //DB에 유니크 제약조건 추가.
        @UniqueConstraint( name="NAME_AGE_UNIQUE", columnNames = {"NAME", "AGE"})})
public class Member {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME", nullable = false, length = 10)
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
