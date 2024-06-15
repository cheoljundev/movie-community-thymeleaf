package hello.moviecomm.repository;

import hello.moviecomm.domain.authority.Authority;
import hello.moviecomm.domain.member.AuthorityMember;
import hello.moviecomm.domain.member.Member;
import hello.moviecomm.domain.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisMemberRepository  implements MemberRepository{
    private final MemberMapper memberMapper;
    private final RoleMapper roleMapper;
    private final AuthorityMapper authorityMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Member save(Member inputMember) {
        Member member = Member.builder()
                .memberId(inputMember.getMemberId())
                .name(inputMember.getName())
                .password(bCryptPasswordEncoder.encode(inputMember.getPassword()))
                .build();
        memberMapper.save(member);
        Integer memberNo = memberMapper.findById(member.getMemberId()).getMemberNo();
        memberMapper.saveRole(memberNo, 1);
        return member;
    }

    @Override
    public AuthorityMember findById(String memberId) {
        Member member = memberMapper.findById(memberId);
        if (member == null) {
            return null;
        }
        List<Role> roles = roleMapper.findByMemberNo(member.getMemberNo());
        List<Authority> auths = new ArrayList<>();
//        for (Role role : roles) {
//            Authority authority = authorityMapper.findByAuthorityCode(role.getAuthorityCode());
//            auths.add(authority);
//        }
        roles.stream()
                .map(role -> authorityMapper.findByAuthorityCode(role.getAuthorityCode()))
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

    @Override
    public List<AuthorityMember> findAll() {
        List<Member> originMembers = memberMapper.findAll();
        List<AuthorityMember> members = new ArrayList<>();
        for (Member member : originMembers) {
            List<Role> roles = roleMapper.findByMemberNo(member.getMemberNo());
            List<Authority> auths = new ArrayList<>();
            roles.stream()
                    .map(role -> authorityMapper.findByAuthorityCode(role.getAuthorityCode()))
                    .forEach(auths::add);
            members.add(AuthorityMember.builder()
                    .memberNo(member.getMemberNo())
                    .memberId(member.getMemberId())
                    .name(member.getName())
                    .password(member.getPassword())
                    .createAt(member.getCreateAt())
                    .auths(auths)
                    .build());
        }
        return members;
    }
}
