package hello.moviecomm.member.controller;

import hello.moviecomm.member.dto.MemberDto;
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
        model.addAttribute("member", new MemberDto());
        return "member/join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute MemberDto member) {
        memberService.save(member);
        return "redirect:/login";
    }
}
