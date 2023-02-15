package com.my.article.service;

import com.my.article.entity.Article;
import com.my.article.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleMapper articleMapper;

    public ArticleService(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    public List<Article> ARTICLE_LISTS() {
        return articleMapper.ARTICLE_LISTS();
    }
    public Article ARTICLE_INFO(Long articleid) {
        return articleMapper.ARTICLE_INFO(articleid);
    }
    public void ARTICLE_NEW(Article article) {
        articleMapper.ARTICLE_NEW(article);
    }
    public void ARTICLE_DELETE(Long articleid) {
        articleMapper.ARTICLE_DELETE(articleid);
    }

}
