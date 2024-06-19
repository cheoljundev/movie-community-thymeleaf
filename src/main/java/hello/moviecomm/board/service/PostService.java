package hello.moviecomm.board.service;

import hello.moviecomm.board.dto.DbListPostDto;
import hello.moviecomm.board.dto.DbPostDto;
import hello.moviecomm.board.dto.ListPostDto;
import hello.moviecomm.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public void save(DbPostDto dbPostDto) {
        postRepository.save(dbPostDto);
    }
    public DbPostDto findByNo(Integer postNo) {
        return postRepository.findByNo(postNo);
    }
    public List<ListPostDto> findAll(Integer boardNo) {
        return postRepository.findAll(boardNo);
    }
}
