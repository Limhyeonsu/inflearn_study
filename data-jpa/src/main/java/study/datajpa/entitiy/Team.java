package study.datajpa.entitiy;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private Long id;
    private String name;

    // 양방향 연관관계에서 연관관계의 주인은 Member2 엔티티이다. 따라서 외래키는 Member2 엔티티가 가지고 있고, Team 엔티티는 읽기만 가능하다.
    @OneToMany(mappedBy = "team")
    List<Member2> member2s = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

}
