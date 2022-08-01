package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {
    private final EntityManager em; // jpa는 EntityManager로 모든게 동작된다.
    // EntityManager는 jpa 의존성을 추가하여 library를 다운받으면 spring boot가 현재 데이터베이스와 연결까지해서 자동으로 생성해준다.
    // -> 이렇게 생성된 것을 injection 받으면 된다.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // persist : 영속하다, 영구 저장하다
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // select m from Member m -> Member as m 으로 m은 alias를 의미. 객체 자체를 조회한다.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
