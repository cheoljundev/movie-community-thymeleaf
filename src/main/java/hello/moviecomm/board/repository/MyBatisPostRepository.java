package hello.moviecomm.board.repository;

import hello.moviecomm.board.dto.DbListPostDto;
import hello.moviecomm.board.dto.DbPostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisPostRepository implements PostRepository{
    private final PostMapper postMapper;
    @Override
    public void save(DbPostDto dbPostDto) {
        postMapper.save(dbPostDto);
    }

    @Override
    public DbPostDto findByNo(Integer postNo) {
        return postMapper.findByNo(postNo);
    }

    @Override
    public List<DbListPostDto> findAll(Integer boardNo) {
        return postMapper.findAll(boardNo);
    }
}
