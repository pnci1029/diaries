package com.example.diary_sample.feature.article.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleSearchDto {
    private String title;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
