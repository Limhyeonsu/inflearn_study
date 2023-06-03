package study.datajpa.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entitiy.Member2;
import study.datajpa.entitiy.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
public class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    @Rollback(false)
    public void testEntity() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member2 member1 = new Member2("member1", 10, teamA);
        Member2 member2 = new Member2("member2", 20, teamA);
        Member2 member3 = new Member2("member3", 30, teamB);
        Member2 member4 = new Member2("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush();
        em.clear();

        List<Member2> member2s = em.createQuery("select m from Member2 m", Member2.class).getResultList();

        for(Member2 member : member2s) {
            System.out.println("member =" + member);
            System.out.println("-> member.team =" + member.getTeam());
        }
    }
}
