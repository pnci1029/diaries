package com.example.diary_sample.feature.article.domain;

import com.example.diary_sample.feature.article.dto.ArticleSearchDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.diary_sample.feature.article.domain.QArticle.article;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Repository @RequiredArgsConstructor @Slf4j
public class ArticleRepositoryImpl implements ArticleRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    @Override
    public List<Article> searchArticles(ArticleSearchDto searchDto) {

        return queryFactory.selectFrom(article)
                .where(searchArticleTitle(searchDto.getTitle()))
                .where(searchArticleContent(searchDto.getContent()))
                .where(searchArticleDate(searchDto.getStartDate(), searchDto.getEndDate()))
                .fetch();
    }

    private BooleanExpression searchArticleTitle(String value) {
        return isEmpty(value) ? null : article.title.like("%"+ value + "%");
    }

    private BooleanExpression searchArticleContent(String value) {
        return isEmpty(value) ? null : article.content.like("%"+ value + "%");
    }

    private BooleanExpression searchArticleDate(LocalDateTime startDate, LocalDateTime endDate) {
        return article.createdAt.between(startDate, endDate);
    }

}
