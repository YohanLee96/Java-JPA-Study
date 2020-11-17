package study.model.Ch06;

import lombok.Setter;

import java.io.Serializable;

@Setter
public class MemberProductId implements Serializable {

    private Long member;
    private String product;



}
