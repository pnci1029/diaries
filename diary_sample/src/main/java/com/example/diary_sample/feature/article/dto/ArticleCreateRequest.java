package com.example.diary_sample.feature.article.dto;

import com.example.diary_sample.feature.image.domain.Image;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ArticleCreateRequest {
    @Nullable
    private List<Image> image;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    private LocalDateTime createdAt;

    public ArticleServiceCreateRequest toService(LocalDateTime createdAt) {
        return ArticleServiceCreateRequest.builder()
                .title(this.title)
                .content(this.content)
                .image(this.image)
                .createdAt(createdAt)
                .build();
    }

    @Builder
    public ArticleCreateRequest(List<Image> image, String title, String content, LocalDateTime createdAt) {
        this.image = image;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }
}
