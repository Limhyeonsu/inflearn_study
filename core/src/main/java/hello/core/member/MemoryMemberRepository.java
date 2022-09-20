package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    //멀티 쓰레드 환경에서는 ConcurrentHashMap을 사용해야 한다.(쓰기 작업시 Lock을 획득하여 동시에 진행하지 못하게 함)
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }

}
