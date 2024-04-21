package com.example.diary_sample.feature.article.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @Builder
public class ArticleSearchDto {
    @Builder.Default
    private String title = "";
    @Builder.Default
    private String content = "";
    @Builder.Default
    private LocalDateTime startDate = LocalDateTime.of(1999,1,1,0,0);
    @Builder.Default
    private LocalDateTime endDate = LocalDateTime.of(2999,1,1,0,0);
}
