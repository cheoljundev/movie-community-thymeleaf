package hello.moviecomm.member.service;

import hello.moviecomm.member.dto.MemberDto;
import hello.moviecomm.member.domain.Member;
import hello.moviecomm.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(Member member) {
        memberRepository.save(member);
    }

    public MemberDto findById(String memberId) {
        return memberRepository.findById(memberId);
    }

    public MemberDto findByNo(Integer memberNo) {
        return memberRepository.findByNo(memberNo);
    }

    public List<MemberDto> findAll() {
        return memberRepository.findAll();
    }
}
