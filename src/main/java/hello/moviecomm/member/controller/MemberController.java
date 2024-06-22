package hello.moviecomm.member.controller;

import hello.moviecomm.member.domain.Member;
import hello.moviecomm.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm() {
        return "member/login";
    }

    @GetMapping("/join")
    public String joinForm(Model model) {
        model.addAttribute("member", new Member());
        return "member/join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute Member member) {
        memberService.save(member);
        return "redirect:/login";
    }
}
