package org.zerock.b01.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.b01.domain.Board;
import org.zerock.b01.domain.QBoard;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {
    // QuerydslRepositorySuppert 부모 클래스 지정
    // BoardSearch의 구현 클래스 (BoardSearch는 Querydsl을 이용할 인터페이스이다.)
    // 이후 기존의 BoardRepository에 부모 인터페이스로 BoardSearch를 지정하면
    // 기존의 Repository와 Querydsl이 연동된다.

    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {

        QBoard board = QBoard.board;    // Q도메인 객체

        JPQLQuery<Board> query = from(board); // select.. from board

        query.where(board.title.contains("1")); // where title like ...

        this.getQuerydsl().applyPagination(pageable, query); // paging

        List<Board> list = query.fetch();

        long count = query.fetchCount();

        return null;
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {

        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        if ( (types != null && types.length > 0) && keyword != null ) { // 검색 조건과 키워드가 있다면

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for (String type : types) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                }
            } // end for
            query.where(booleanBuilder);
        } // end if

        // bno > 0
        query.where(board.bno.gt(0L));

        // paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }
}
