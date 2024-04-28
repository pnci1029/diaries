package com.example.diary_sample.feature.article.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @NoArgsConstructor
public class ArticleCreateRequest {
    @NotEmpty(message = "이미지 속성은 필수입니다.")
    @NotNull
    private List<String> images = new ArrayList<>();
    @NotBlank(message = "제목 기입은 필수입니다.")
    private String title;
    @NotBlank(message = "내용 기입은 필수입니다.")
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
