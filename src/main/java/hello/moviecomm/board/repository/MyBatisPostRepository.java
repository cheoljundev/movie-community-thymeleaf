package hello.moviecomm.board.repository;

import hello.moviecomm.board.dto.DbListPostDto;
import hello.moviecomm.board.dto.DbPostDto;
import hello.moviecomm.board.dto.ListPostDto;
import hello.moviecomm.board.dto.ModifyPostDto;
import hello.moviecomm.member.repository.MemberRepository;
import hello.moviecomm.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisPostRepository implements PostRepository{
    private final PostMapper postMapper;
    private final MemberService memberService;
    @Override
    public void save(DbPostDto dbPostDto) {
        postMapper.save(dbPostDto);
    }

    @Override
    public DbPostDto findByNo(Integer postNo) {
        return postMapper.findByNo(postNo);
    }

    @Override
    public List<ListPostDto> findAll(Integer boardNo) {
        List<DbListPostDto> dbListPostDtoList = postMapper.findAll(boardNo);
        List<ListPostDto> listPostDtoList = new ArrayList<>();
        dbListPostDtoList.stream().forEach(dbListPostDto -> {
            Integer memberNo = dbListPostDto.getMemberNo();
            listPostDtoList.add(ListPostDto.builder()
                    .postNo(dbListPostDto.getPostNo())
                    .title(dbListPostDto.getTitle())
                    .memberName(memberService.findByNo(memberNo).getName())
                    .createAt(dbListPostDto.getCreateAt())
                    .build());
        });
        return listPostDtoList;
    }

    @Override
    public void remove(Integer postNo) {
        postMapper.remove(postNo);
    }

    @Override
    public void modify(ModifyPostDto modifyPostDto, Integer postNo) {
        postMapper.modify(modifyPostDto, postNo);
    }
}
