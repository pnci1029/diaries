package com.example.diary_sample.feature.article.dto;

import com.example.diary_sample.feature.article.domain.Article;
import com.example.diary_sample.feature.image.domain.Image;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ArticleResponseDto {
    private Long articleIdx;
    private String title;
    private String content;
    private Long views;
    private LocalDateTime createdAt;
//    private List<Image> images;

    @Builder
    private ArticleResponseDto(Long articleIdx, String title, String content, Long views, LocalDateTime createdAt, List<Image> images) {
        this.articleIdx = articleIdx;
        this.title = title;
        this.content = content;
        this.views = views;
        this.createdAt = createdAt;
//        this.images = images;
    }


    public static ArticleResponseDto of(Article article) {
        return ArticleResponseDto.builder()
                .articleIdx(article.getArticleId())
                .title(article.getTitle())
                .content(article.getContent())
                .createdAt(article.getCreatedAt())
                .images(article.getImages())
                .build();
    }
}
