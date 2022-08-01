package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// interface는 다중 상속이 가능하다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { // interface에 interface(JpaRepository<T, id(pk) type>)를 받을때에는 extends를 사용한다.

    @Override
    Optional<Member> findByName(String name);
}
