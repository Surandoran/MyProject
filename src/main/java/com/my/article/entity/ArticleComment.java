package com.my.article.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@SequenceGenerator(
        name="Article_Comment_SEQ_GEN", //시퀀스 제너레이터 이름
        sequenceName="Article_Comment_SEQ", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
)
public class ArticleComment {

    @Id
    @GeneratedValue(
            strategy= GenerationType.SEQUENCE, //사용할 전략을 시퀀스로  선택
            generator="Article_Comment_SEQ_GEN" //식별자 생성기를 설정해놓은  USER_SEQ_GEN으로 설정
    )
    private Long commentid;
    private Long articleid;
    private String content;
    private String email;
    private String articlecreate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

}
