package com.my.article.controller;

import com.my.article.entity.Article;
import com.my.article.entity.ArticleComment;
import com.my.article.entity.Customer;
import com.my.article.service.ArticleCommentService;
import com.my.article.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleCommentService articleCommentService;
    public static Long id;

    public ArticleController(@Autowired ArticleService articleService,
                             @Autowired ArticleCommentService articleCommentService) {
        this.articleService = articleService;
        this.articleCommentService = articleCommentService;
    }

    @GetMapping("/articles")
    public String Articles(@PageableDefault(
            size = 15,
            sort = "articleid",
            direction = Sort.Direction.DESC) Pageable pageable, Model model) throws Exception {

        try {

            List<Article> articles = articleService.ARTICLE_LISTS();

            final int start = (int) pageable.getOffset();
            final int end = Math.min((start + pageable.getPageSize()), articles.size());
            final Page<Article> result = new PageImpl<>(articles.subList(start, end), pageable, articles.size());

            log.info("자유게시판 현재 페이지 : " + pageable.getPageNumber());

            model.addAttribute("list", result);

            if (articles.size() != 0) {
                return "article/articles";
            } else {
                return "error/Exception";
            }

        } catch (Exception e) {
            log.info("에러 내용 : " + e.getMessage());
            return "error/Exception";
        }

    }

    @GetMapping("/article/new")
    public String ARTICLE_NEWPAGE() {
        return "/article/new";
    }

    @PostMapping("/article/new")
    public String ARTICLE_NEW(Article article) {

        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            log.info("principal={}", principal);
            String email = ((Customer) principal).getEmail();

            String articlecreate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            Article article1 = new Article();
            article1.setArticleid(article.getArticleid());
            article1.setTitle(article.getTitle());
            article1.setContent(article.getContent());
            article1.setEmail(email);
            article1.setArticlecreate(articlecreate);

            articleService.ARTICLE_NEW(article1);

            if (article1.getTitle() != null) {
                log.info("작성한 article : " + article1);

                return "redirect:/articles";
            } else {
                return "/article/new";
            }
        } catch (Exception e) {
            log.info("에러 내용 : " + e.getMessage());
            return "/article/new";
        }

    }

    @GetMapping("/article/{articleid}")
    public String Article_info(@PageableDefault(
            size = 30,
            sort = "articleid",
            direction = Sort.Direction.DESC) Pageable pageable,
                               @PathVariable Long articleid,
                               Model model,
                               Article article) {

        try {

            id = article.getArticleid();
            articleid = article.getArticleid();

            article = articleService.ARTICLE_INFO(articleid);
            List<ArticleComment> comments = articleCommentService.ARTICLE_COMMENTS(articleid);

            final int start = (int) pageable.getOffset();
            final int end = Math.min((start + pageable.getPageSize()), comments.size());
            final Page<ArticleComment> result = new PageImpl<>(comments.subList(start, end), pageable, comments.size());

            log.info("id 번호 : " + article.getArticleid());
            log.info("article : " + article);

            model.addAttribute("article", article);
            model.addAttribute("comments", result);

            if (article.getArticleid() != null) {
                return "article/article";
            } else {
                return "error/Exception";
            }
        } catch (Exception e) {
            log.info("에러 내용 : " + e.getMessage());
            return "error/Exception";
        }
    }

    @PostMapping("/comment/new")
    public String COMMNET_NEW(ArticleComment articleComment, Article article) {

        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            log.info("principal={}", principal);
            String email = ((Customer) principal).getEmail();

            String articlecreate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            ArticleComment articleComment1 = new ArticleComment();
            articleComment1.setCommentid(articleComment.getCommentid());
            articleComment1.setArticleid(id);
            articleComment1.setContent(articleComment.getContent());
            articleComment1.setEmail(email);
            articleComment1.setArticlecreate(articlecreate);
            log.info("작성한 comment : " + articleComment1);

            articleCommentService.ARTICLE_COMMENT_NEW(articleComment1);
            if (articleComment1.getArticleid() != null) {

                log.info("작성한 comment : " + articleComment1);

                return "redirect:/article/" + id;
            } else {
                return "/error/Exception";
            }
        } catch (Exception e) {
            log.info("에러 내용 : " + e.getMessage());
            return "/error/Exception";
        }
    }

    @PostMapping("/article/delete/{articleid}")
    public String ARTICLE_DELETE(@PathVariable Long articleid) {
        try {
            articleid = id;

            articleService.ARTICLE_DELETE(articleid);
            articleCommentService.ARTICLE_COMMENT_DELETE(articleid);

            if (articleid != null) {

                log.info("삭제한 articleid : " + articleid);

                return "redirect:/articles";
            } else {
                return "/error/Exception";
            }
        } catch (Exception e) {
            log.info("에러 내용 : " + e.getMessage());
            return "/error/Exception";
        }

    }
}