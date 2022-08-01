package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // spring container가 뜰 때 @Controller annotation이 있으면 객체로 생성을 해서 스프링이 빈으로 가지고 있는다.
// 이렇게 하는 것이 compoment 스캔과 자동 의존관계를 설정하는 것.
public class MemberController {
    // new로 생성하지 않고 spring container에 등록을 하고 사용한다. -> spring container에 등록하면 하나만 등록된다.
    private final MemberService memberService;

    @Autowired // @Autowired : spring이 spring container에 있는 memberService와 매개로 들어있는 memberService를 자동으로 연결을 시켜준다.
    // -> 자동으로 연결(wired)시켜줄때 MemberService가 스프링에 빈으로 등록되어 있지않으면 MemberService를 찾을 수 없다는 에러가 날 것이다.
    public MemberController(MemberService memberService) { // spring이 controller를 객체로 생성할때 해당 생성자를 호출하여 (빈으로 등록되어 있는) memberService를 주입해준다.
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
