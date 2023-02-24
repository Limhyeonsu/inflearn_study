package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)    //junit4에 스프링 올려서 테스트할 때 필요
@SpringBootTest     //스프링 컨테이너 안에서 테스트를 돌리고자 할 때
@Transactional      //트랜잭션을 걸어놓고 테스트시 rollback 처리 한다.
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long memberId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(memberId));
    }

    @Test(expected = IllegalStateException.class) //exception이 발생해야 성공
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2);

        //then
        fail("예외가 발생해야 한다.");
    }
}