package com.example.diary_sample.feature.article.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ArticleCreateRequest {
    @NotNull
    private List<String> images;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    private LocalDateTime createdAt;

    public ArticleServiceCreateRequest toService(LocalDateTime createdAt) {
        return ArticleServiceCreateRequest.builder()
                .title(this.title)
                .content(this.content)
                .images(this.images)
                .createdAt(createdAt)
                .build();
    }

    @Builder
    public ArticleCreateRequest(List<String> images, String title, String content, LocalDateTime createdAt) {
        this.images = images;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }
}
