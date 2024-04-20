package com.example.diary_sample.feature.article.domain;

import com.example.diary_sample.feature.article.dto.ArticleServiceCreateRequest;
import com.example.diary_sample.feature.image.domain.Image;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    private int views;
    private String title;
    private String content;
    @OneToMany(mappedBy = "article")
    private List<Image> images;

    private LocalDateTime createdAt;

    @Builder
    private Article(int views, String title, String content, List<Image> images) {
        this.views = views;
        this.title = title;
        this.content = content;
        this.images = images;
        this.createdAt = LocalDateTime.now();
    }

    public static Article create(ArticleServiceCreateRequest request) {
        return Article.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .images(request.getImage())
                .views(0)
                .build();
    }

}
