package study.model.Ch06;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "COMPLEX_PK_MEMBER")
@NoArgsConstructor
public class ComplexPkMember {

    @Id
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> products = new ArrayList<>();

    public ComplexPkMember(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
