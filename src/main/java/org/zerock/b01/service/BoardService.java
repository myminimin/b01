package org.zerock.b01.service;

import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.BoardListReplyCountDTO;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;

public interface BoardService {

    Long register(BoardDTO boardDTO); // 작성

    BoardDTO readOne(Long bno); // 조회
    
    void modify(BoardDTO boardDTO); // 수정
    
    void remove(Long bno); // 삭제

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO); // 목록, 검색 기능

    //댓글의 숫자까지 처리
    PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);
}
