package com.example.diary_sample.feature.article.domain;

import com.example.diary_sample.feature.article.dto.ArticleSearchDto;

import java.util.List;

public interface ArticleRepositoryCustom {

    List<Article> searchArticles(ArticleSearchDto searchDto);
}
