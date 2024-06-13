package hello.moviecomm.repository;

import hello.moviecomm.domain.member.Authority;
import hello.moviecomm.domain.member.AuthorityMember;
import hello.moviecomm.domain.member.Member;
import hello.moviecomm.domain.member.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final MemberMapper memberMapper;

    public AuthorityMember findByMemberId(String memberId) {
        Member member = memberMapper.findById(memberId);
        if (member == null) {
            return null;
        }
        List<Role> roles = memberMapper.findRolesByNo(member.getMemberNo());
        List<Authority> auths = new ArrayList<>();
//        for (Role role : roles) {
//            Authority authority = memberMapper.findAuthorityByCode(role.getAuthorityCode());
//            auths.add(authority);
//        }
        roles.stream()
                .map(role -> memberMapper.findAuthorityByCode(role.getAuthorityCode()))
                .forEach(auths::add);
        return AuthorityMember.builder()
                .memberNo(member.getMemberNo())
                .memberId(member.getMemberId())
                .name(member.getName())
                .password(member.getPassword())
                .createAt(member.getCreateAt())
                .auths(auths)
                .build();
    }
}
