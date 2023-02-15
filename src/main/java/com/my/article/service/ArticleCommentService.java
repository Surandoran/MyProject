package com.my.article.service;

import com.my.article.entity.Article;
import com.my.article.entity.ArticleComment;
import com.my.article.mapper.ArticleCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleCommentService {

    private final ArticleCommentMapper articleCommentMapper;


    public ArticleCommentService(@Autowired ArticleCommentMapper articleCommentMapper) {
        this.articleCommentMapper = articleCommentMapper;
    }

    public List<ArticleComment> ARTICLE_COMMENTS_ALL() {
        return articleCommentMapper.ARTICLE_COMMENTS_ALL();
    }

    public List<ArticleComment> ARTICLE_COMMENTS(Long articleid) {
        return articleCommentMapper.ARTICLE_COMMENTS(articleid);
    }

    public void ARTICLE_COMMENT_NEW(ArticleComment articleComment) {
        articleCommentMapper.ARTICLE_COMMENT_NEW(articleComment);
    }

    public void ARTICLE_COMMENT_DELETE(Long articleid) {
        articleCommentMapper.ARTICLE_COMMENT_DELETE(articleid);
    }

}
