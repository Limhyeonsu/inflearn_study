package hello.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    /* 설계 변경으로 MemberRepository 인터페이스에만 의존한다. 생성자를 통해 어떤 구현 객체가 주입될지는 오직 외부(AppConfig)에서 결정된다.
       MemberServiceImpl은 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중하면 된다.
     */
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }


}
