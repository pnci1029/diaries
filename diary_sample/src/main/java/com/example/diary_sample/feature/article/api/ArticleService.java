package com.example.diary_sample.feature.article.api;

import com.example.diary_sample.feature.article.domain.Article;
import com.example.diary_sample.feature.article.domain.ArticleRepository;
import com.example.diary_sample.feature.article.dto.ArticleSearchDto;
import com.example.diary_sample.feature.article.dto.ArticleServiceCreateRequest;
import com.example.diary_sample.global.util.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor @Slf4j
public class ArticleService {
    private final ArticleRepository articleRepository;
    public Response<?> getAllArticles(ArticleSearchDto request) {
        return Response.result(articleRepository.findAll(), HttpStatus.OK);
    }

    public Response<?> createArticle(ArticleServiceCreateRequest request) {
        Article article = Article.create(request);
        return Response.result(articleRepository.save(article), HttpStatus.OK);
    }
}
