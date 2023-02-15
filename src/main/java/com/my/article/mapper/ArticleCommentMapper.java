package com.my.article.mapper;

import com.my.article.entity.ArticleComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleCommentMapper {

    List<ArticleComment> ARTICLE_COMMENTS_ALL();
    List<ArticleComment> ARTICLE_COMMENTS(Long articleid);
    void ARTICLE_COMMENT_NEW(ArticleComment articleComment);
    void ARTICLE_COMMENT_DELETE(Long articleid);


}
