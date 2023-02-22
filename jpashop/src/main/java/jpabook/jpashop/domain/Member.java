package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    // 단순 값 타입
    @Embedded
    private Address address;

    // mappedBy 양방향 연관관계시 연관관계의 주인이 아닌 쪽에 단순 읽기 전용으로 설정하기 위한 것, 연관관계의 주인쪽 필드 이름으로 설정한다.
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
