package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional // jpa는 항상 transaction이 있어야한다.
//@Service // spring container가 뜰때 스프링이 @Service를 보고 빈으로 등록해준다.
public class MemberService {
    private final MemberRepository memberRepository; // new 로 객체를 생성하던 것을 constructor로 교체
    // -> new 로 생성하고 test에서도 new로 생성하면 서로 다른 객체가 생성되는 것이기에 생성자 주입으로 변경

//    @Autowired
    public MemberService(MemberRepository memberRepository) { // memberRepository를 외부에서 주입받음
        // -> Dependency Injection(DI)
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) { // 회원 가입
        // 같은 이름 중복 검증
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다."); // 부적합한 상태 에러
                });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
