package com.example.diary.feature.article.dto;

import com.example.diary.feature.image.domain.Image;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ArticleServiceCreateRequest {

    @Nullable
    private List<Image> image;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;

    @Builder
    private ArticleServiceCreateRequest(List<Image> image, String title, String content) {
        this.image = image;
        this.title = title;
        this.content = content;
    }
}
