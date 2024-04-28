package com.example.diary_sample.feature.image.domain;

import com.example.diary_sample.feature.article.domain.Article;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;
    private String imgName;
    private String imgPath;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articleId")
    private Article article;


    @Builder
    private Image(String imgName, String imgPath, Article article) {
        this.imgName = imgName;
        this.imgPath = imgPath;
        this.article = article;
    }


    public static Image create(String image, Article article) {
        if (image.isEmpty()) {
            throw new IllegalArgumentException("이미지를 추가해주세요");
        }
        return Image.builder()
                .article(article)
                .imgPath(image)
                .imgName(image)
                .build();
    }
}
