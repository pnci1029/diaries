package com.example.diary_sample.feature.article.dto;

import com.example.diary_sample.feature.image.domain.Image;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ArticleServiceCreateRequest {

    @Nullable
    private List<String> images;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    private LocalDateTime createdAt;

    @Builder
    private ArticleServiceCreateRequest(List<String> images, String title, String content, LocalDateTime createdAt) {
        this.images = images;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }
}
