INSERT INTO ARTICLE(articleid, email, content, title, articlecreate)
VALUES (ARTICLE_SEQ.NEXTVAL, 'LOVE@NAVER.COM', '내용입니다.', '제목입니다.', '2023-02-12');

INSERT INTO ARTICLE(articleid, email, content, title, articlecreate)
VALUES (ARTICLE_SEQ.NEXTVAL, 'title@NAVER.COM', '내용입니다.', '제목입니다.', '2023-02-13');

INSERT INTO ARTICLE(articleid, email, content, title, articlecreate)
VALUES (ARTICLE_SEQ.NEXTVAL, 'content@NAVER.COM', '내용입니다.', '제목입니다.', '2023-02-14');

INSERT INTO ARTICLE(articleid, email, content, title, articlecreate)
VALUES (ARTICLE_SEQ.NEXTVAL, 'PO@NAVER.COM', '내용입니다.', '제목입니다.', '2023-02-15');

INSERT INTO ARTICLE_COMMENT(commentid, articleid, content, email, articlecreate)
VALUES (ARTICLE_COMMENT_SEQ.NEXTVAL, 1, '내용입니다.', 'LOVE@NAVER.COM', '2023-02-12');

INSERT INTO ARTICLE_COMMENT(commentid, articleid, content, email, articlecreate)
VALUES (ARTICLE_COMMENT_SEQ.NEXTVAL, 2, '내용입니다.', 'title@NAVER.COM', '2023-02-12');

INSERT INTO ARTICLE_COMMENT(commentid, articleid, content, email, articlecreate)
VALUES (ARTICLE_COMMENT_SEQ.NEXTVAL, 3, '내용입니다.', 'content@NAVER.COM', '2023-02-12');

INSERT INTO ARTICLE_COMMENT(commentid, articleid, content, email, articlecreate)
VALUES (ARTICLE_COMMENT_SEQ.NEXTVAL, 4, '내용입니다.', 'PO@NAVER.COM', '2023-02-12');

INSERT INTO CUSTOMER(id, name, password, email, phone, auth)
VALUES(CUSTOMER_SEQ.NEXTVAL, '홍길동', '1234', 'LOVE@NAVER.COM', '010-0000-0000', 'USER');

INSERT INTO CUSTOMER(id, name, password, email, phone, auth)
VALUES(CUSTOMER_SEQ.NEXTVAL, '일길동', '1234', 'title@NAVER.COM', '010-1111-1111', 'USER');

INSERT INTO CUSTOMER(id, name, password, email, phone, auth)
VALUES(CUSTOMER_SEQ.NEXTVAL, '이길동', '1234', 'content@NAVER.COM', '010-2222-2222', 'USER');

INSERT INTO CUSTOMER(id, name, password, email, phone, auth)
VALUES(CUSTOMER_SEQ.NEXTVAL, '삼길동', '1234', 'PO@NAVER.COM', '010-3333-3333', 'USER');