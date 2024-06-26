package hello.moviecomm.member.service;

import hello.moviecomm.member.dto.MemberDto;
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
        MemberDto memberDto = memberRepository.findById(username);
        if (memberDto == null) {
            throw new UsernameNotFoundException("회원 찾을 수 없음");
        }

        return new CustomUserDetails(memberDto);
    }
}
