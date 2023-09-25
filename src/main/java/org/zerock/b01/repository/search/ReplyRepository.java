package org.zerock.b01.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.b01.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    // 게시판과 별개로 CRUD가 일어날 수 있기 때문에 별도의 Repository를 작성


    @Query("select r from Reply r where r.board.bno = :bno")
    Page<Reply> listOfBoard(Long bno, Pageable pageable);
    // 특정한 페이지의 댓글들은 페이징 처리

    void deleteByBoard_Bno(Long bno);
}
