package study.datajpa.entitiy;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})
public class Member2 {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member2(String username) {
        this(username, 0);
    }

    public Member2(String username, int age){
        this(username, age, null);
    }

    public Member2(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if(team != null) {
            changeTeam(team);
        }
    }

    // 양방향 연관관계 한번에 처리
    public void changeTeam(Team team) {
        this.team = team;
        team.getMember2s().add(this);
    }
}
