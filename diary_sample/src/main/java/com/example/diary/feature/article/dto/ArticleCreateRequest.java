package com.example.diary.feature.article.dto;

import com.example.diary.feature.image.domain.Image;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ArticleCreateRequest {
    @Nullable
    private List<Image> image;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;

    public ArticleServiceCreateRequest toService() {
        return ArticleServiceCreateRequest.builder()
                .title(this.title)
                .content(this.content)
                .image(this.image)
                .build();
    }

    @Builder
    public ArticleCreateRequest(List<Image> image, String title, String content) {
        this.image = image;
        this.title = title;
        this.content = content;
    }
}
