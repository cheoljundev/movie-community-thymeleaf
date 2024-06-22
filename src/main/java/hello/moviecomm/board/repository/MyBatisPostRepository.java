package hello.moviecomm.board.repository;

import hello.moviecomm.board.domain.Post;
import hello.moviecomm.board.dto.PostListDto;
import hello.moviecomm.board.dto.PostModifyDto;
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
    public void save(Post post) {
        postMapper.save(post);
    }

    @Override
    public Post findByNo(Integer postNo) {
        return postMapper.findByNo(postNo);
    }

    @Override
    public List<PostListDto> findAll(Integer boardNo) {
        List<Post> dbListPostDtoList = postMapper.findAll(boardNo);
        List<PostListDto> listPostDtoList = new ArrayList<>();
        dbListPostDtoList.stream().forEach(dbListPostDto -> {
            Integer memberNo = dbListPostDto.getMemberNo();
            listPostDtoList.add(PostListDto.builder()
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
    public void modify(PostModifyDto postModifyDto, Integer postNo) {
        postMapper.modify(postModifyDto, postNo);
    }
}
