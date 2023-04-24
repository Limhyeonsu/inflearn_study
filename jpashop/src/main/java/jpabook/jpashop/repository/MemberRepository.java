package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository //스프링 빈으로 등록
@RequiredArgsConstructor
public class MemberRepository {

    //JPA 엔티티 매니저를 스프링이 생성한 엔티티 매니저를 주입해준다.
//    @PersistenceContext 원래는 이 애너테이션으로 주입해야하나 스프링에서 @RequiredArgsConstructor을 통한 주입을 해준다.
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }


}
