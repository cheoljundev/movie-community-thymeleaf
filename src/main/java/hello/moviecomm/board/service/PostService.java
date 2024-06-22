package hello.moviecomm.board.service;

import hello.moviecomm.board.domain.Post;
import hello.moviecomm.board.dto.PostListDto;
import hello.moviecomm.board.dto.PostWriteDto;
import hello.moviecomm.board.exception.AccessDeniedException;
import hello.moviecomm.board.repository.PostRepository;
import hello.moviecomm.member.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    String projectPath = System.getProperty("user.dir");
    @Value("${file.dir}") String fileDir;
    public void save(PostWriteDto postWriteDto) throws IOException {
        MultipartFile file = postWriteDto.getFile();
        Post post = Post.builder()
                .title(postWriteDto.getTitle())
                .content(postWriteDto.getContent())
                .memberNo(postWriteDto.getMemberNo())
                .boardNo(postWriteDto.getBoardNo())
                .build();

        if (isFisFileValidile(file)) {
            saveFile(file, post);
        }

        postRepository.save(post);
    }

    public Post findByNo(Integer postNo) {
        return postRepository.findByNo(postNo);
    }

    public List<PostListDto> findAll(Integer boardNo) {
        return postRepository.findAll(boardNo);
    }

    public void remove(Integer postNo, CustomUserDetails customUserDetails) {

        if (customUserDetails == null) {
            throw new AccessDeniedException("게시글 삭제 권한이 없습니다.");
        }

        Post post = findByNo(postNo);
        Integer memberNo = customUserDetails.getMember().getMemberNo();
        boolean isAdmin = customUserDetails.hasRole("ROLE_ADMIN");
        if (post.getMemberNo() == memberNo || isAdmin) {
            postRepository.remove(postNo);
        } else {
            throw new AccessDeniedException("게시글 삭제 권한이 없습니다.");
        }
    }

    private static boolean isFisFileValidile(MultipartFile file) {
        return file != null && !file.isEmpty();
    }
    private void saveFile(MultipartFile file, Post post) throws IOException {
        // 파일 이름 추출
        String fileName = file.getOriginalFilename();

        // 파일 이름 설정을 위한 UUID 생성
        String uuid = UUID.randomUUID().toString();

        // 확장자 추출
        int extIndex = fileName.lastIndexOf(".") + 1;
        String ext = fileName.substring(extIndex);
        String storeFileName = uuid + "." + ext;

        // dbPostDto에 파일 정보 저장
        post.setFileName(fileName);
        post.setStoreFileName(storeFileName);

        // 파일 저장 경로
        String filePath = projectPath + fileDir + storeFileName;

        // 파일 디렉토리가 없으면 생성
        File directory = new File(projectPath + fileDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 파일 저장
        file.transferTo(new File(filePath));
    }
}
