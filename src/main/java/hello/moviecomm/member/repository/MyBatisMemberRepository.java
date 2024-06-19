package hello.moviecomm.member.repository;

import hello.moviecomm.member.domain.Authority;
import hello.moviecomm.member.domain.Member;
import hello.moviecomm.member.dto.MemberDto;
import hello.moviecomm.member.domain.Role;
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
    public MemberDto save(MemberDto inputMemberDto) {
        MemberDto memberDto = MemberDto.builder()
                .memberId(inputMemberDto.getMemberId())
                .name(inputMemberDto.getName())
                .password(bCryptPasswordEncoder.encode(inputMemberDto.getPassword()))
                .build();
        memberMapper.save(memberDto);
        Integer memberNo = memberMapper.findById(memberDto.getMemberId()).getMemberNo();
        memberMapper.saveRole(memberNo, 1);
        return memberDto;
    }

    @Override
    public Member findById(String memberId) {
        MemberDto memberDto = memberMapper.findById(memberId);
        if (memberDto == null) {
            return null;
        }
        List<Role> roles = roleMapper.findByMemberNo(memberDto.getMemberNo());
        List<Authority> auths = new ArrayList<>();
//        for (Role role : roles) {
//            Authority authority = authorityMapper.findByAuthorityCode(role.getAuthorityCode());
//            auths.add(authority);
//        }
        roles.stream()
                .map(role -> authorityMapper.findByAuthorityCode(role.getAuthorityCode()))
                .forEach(auths::add);
        return Member.builder()
                .memberNo(memberDto.getMemberNo())
                .memberId(memberDto.getMemberId())
                .name(memberDto.getName())
                .password(memberDto.getPassword())
                .createAt(memberDto.getCreateAt())
                .auths(auths)
                .build();
    }

    @Override
    public Member findByNo(Integer memberNo) {
        MemberDto memberDto = memberMapper.findByNo(memberNo);
        if (memberDto == null) {
            return null;
        }
        List<Role> roles = roleMapper.findByMemberNo(memberDto.getMemberNo());
        List<Authority> auths = new ArrayList<>();
        roles.stream()
                .map(role -> authorityMapper.findByAuthorityCode(role.getAuthorityCode()))
                .forEach(auths::add);
        return Member.builder()
                .memberNo(memberDto.getMemberNo())
                .memberId(memberDto.getMemberId())
                .name(memberDto.getName())
                .password(memberDto.getPassword())
                .createAt(memberDto.getCreateAt())
                .auths(auths)
                .build();
    }

    @Override
    public List<Member> findAll() {
        List<MemberDto> originMemberDtos = memberMapper.findAll();
        List<Member> members = new ArrayList<>();
        for (MemberDto memberDto : originMemberDtos) {
            List<Role> roles = roleMapper.findByMemberNo(memberDto.getMemberNo());
            List<Authority> auths = new ArrayList<>();
            roles.stream()
                    .map(role -> authorityMapper.findByAuthorityCode(role.getAuthorityCode()))
                    .forEach(auths::add);
            members.add(Member.builder()
                    .memberNo(memberDto.getMemberNo())
                    .memberId(memberDto.getMemberId())
                    .name(memberDto.getName())
                    .password(memberDto.getPassword())
                    .createAt(memberDto.getCreateAt())
                    .auths(auths)
                    .build());
        }
        return members;
    }
}
