package com.my.article.mapper;

import com.my.article.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper
public interface ArticleMapper {

    List<Article> ARTICLE_LISTS();
    Article ARTICLE_INFO(Long articleid);
    void ARTICLE_NEW(Article article);
    void ARTICLE_DELETE(Long articleid);

}
