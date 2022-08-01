package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 메서드가 끝날때마다 동작을 해라
    public void afterEach() {
        repository.clearStore(); // 데이터 삭제
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}

// findAll까지 모두 작성후 전체 테스트를 진행하면 findByname()에서 에러 발생 ->
// 테스트 시 메서드 실행 순서에 대한 보장은 없다. findAll() 메서드가 먼저 실행되는데, 이때 이미 같은 이름의 member 객체가 저장이 되기때문에
// 다음 순서인 findByName()에서 이전에 저장된 동일한 이름의 member를 먼저 찾아오기때문에 객체가 맞지않다고 assertj가 에러를 뱉는다.
// 이를 해결하기 위해서는 테스트가 하나 끝날때마다 데이터를 clear를 해주면 된다.
