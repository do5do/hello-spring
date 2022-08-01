package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository // 마친가지로 spring container가 @Repository를 보고 빈으로 등록을 해준다.
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>(); // 실무에서는 동시성 문제가 있기때문에 ConCurrentHashMap<>()을 사용해야 한다.
    private static long sequence = 0L;

//    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // loop를 돌면서 filter에 걸리는 객체를 반환해줌. 끝까지 돌았는데 없으면 null이 포함이 돼서 반환이 됨.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
