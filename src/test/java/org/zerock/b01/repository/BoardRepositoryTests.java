package org.zerock.b01.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.b01.domain.Board;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1,100).forEach(i -> {
            Board board = Board.builder()
                    .title("title..." +i)
                    .content("content..." +i)
                    .writer("user" +(i%10))
                    .build();

            Board result = boardRepository.save(board);
            // insert를 실행하는 기능은 JpaRepository의 save()를 통해서 이루어 진다.
            // save()는 현재의 영속 컨텍스트 내에 데이터가 존재하는지 찾아보고 해당 엔티티 객체가
            // 없을 때는 insert를, 존재할 때는 update를 자동으로 실행한다.
            log.info("BNO: " + result.getBno());
        });
    }

    @Test
    public void testSelect() {
        Long bno = 100L;

        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow();

        log.info(board);
    }
}
