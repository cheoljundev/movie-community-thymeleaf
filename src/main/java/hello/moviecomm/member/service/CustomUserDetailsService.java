package hello.moviecomm.member.service;

import hello.moviecomm.member.domain.Member;
import hello.moviecomm.member.dto.CustomUserDetails;
import hello.moviecomm.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username = " + username);
        Member member = memberRepository.findById(username);
        System.out.println("member = " + member);
        if (member == null) {
            throw new UsernameNotFoundException("회원 찾을 수 없음");
        }

        return new CustomUserDetails(member);
    }
}
