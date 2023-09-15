package org.zerock.b01.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity // 엔티티 객체를 위한 엔티티 클래스는 반드시 @Entity가 있어야 한다.
@Getter
@Builder    // @AllArgs~, @NoArgs~랑 세트구성
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board extends BaseEntity {

    @Id //엔티티 객체의 구분을 위한 @Id도 필수이다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @Column(length = 500, nullable = false) // 컬럼의 길이와 null 허용 여부
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String writer;

    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }   // Entity 클래스는 가능하면 최소한의 변경이나 불변(immuatble)하게 설계하는 것이 좋지만
        // 강제적인 사항은 아니다. Board의 경우 '제목/내용' 정도는 수정이 가능하므로 change() 라는 메소드를 추가한다.
}
