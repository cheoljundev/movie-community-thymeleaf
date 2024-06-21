package hello.moviecomm.board.service;

import hello.moviecomm.board.dto.DbPostDto;
import hello.moviecomm.board.dto.ListPostDto;
import hello.moviecomm.board.dto.WritePostDto;
import hello.moviecomm.board.repository.PostRepository;
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
    public void save(WritePostDto writePostDto) throws IOException {
        MultipartFile file = writePostDto.getFile();
        DbPostDto dbPostDto = DbPostDto.builder()
                .title(writePostDto.getTitle())
                .content(writePostDto.getContent())
                .memberNo(writePostDto.getMemberNo())
                .boardNo(writePostDto.getBoardNo())
                .build();

        if (isFisFileValidile(file)) {
            saveFile(file, dbPostDto);
        }

        postRepository.save(dbPostDto);
    }

    public DbPostDto findByNo(Integer postNo) {
        return postRepository.findByNo(postNo);
    }

    public List<ListPostDto> findAll(Integer boardNo) {
        return postRepository.findAll(boardNo);
    }

    private static boolean isFisFileValidile(MultipartFile file) {
        return file != null && !file.isEmpty();
    }
    private void saveFile(MultipartFile file, DbPostDto dbPostDto) throws IOException {
        // 파일 이름 추출
        String fileName = file.getOriginalFilename();

        // 파일 이름 설정을 위한 UUID 생성
        String uuid = UUID.randomUUID().toString();

        // 확장자 추출
        int extIndex = fileName.lastIndexOf(".") + 1;
        String ext = fileName.substring(extIndex);
        String storeFileName = uuid + "." + ext;

        // dbPostDto에 파일 정보 저장
        dbPostDto.setFileName(fileName);
        dbPostDto.setStoreFileName(storeFileName);

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
