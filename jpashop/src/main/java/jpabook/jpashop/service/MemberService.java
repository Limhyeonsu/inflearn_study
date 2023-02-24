package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional //트랜잭션을 설정해야 한다. 클래스 레벨에서 사용하면 public 메서드에는 자동으로 붙는다.
@Transactional(readOnly = true) //조회의 경우 readOnly로 설정하면 성능 최적화가 된다.
@RequiredArgsConstructor //final이 붙은 필드를 자동으로 생성자를 생성해준다.(생성자 주입방식)
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional //쓰기의 경우 readOnly 설정을 하면 안 된다.
    public Long join(Member member){
        //중복 회원 검증
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId(); //em.persist() > 영속성 컨텍스트에 올라가고 PK가 생성됨
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long id) {
        return memberRepository.findOne(id);
    }
}
