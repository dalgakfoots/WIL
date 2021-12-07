package jpabook.jpashop.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @JsonIgnore // 양방향 관계일 시, 무한루프 걸릴 가능성 높음
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();



}
