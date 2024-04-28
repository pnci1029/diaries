package com.example.diary_sample.feature.article.api;

import com.example.diary_sample.feature.article.domain.Article;
import com.example.diary_sample.feature.article.domain.ArticleRepository;
import com.example.diary_sample.feature.article.dto.ArticleResponseDto;
import com.example.diary_sample.feature.article.dto.ArticleSearchDto;
import com.example.diary_sample.feature.article.dto.ArticleServiceCreateRequest;
import com.example.diary_sample.feature.image.domain.Image;
import com.example.diary_sample.feature.image.domain.ImageRepository;
import com.example.diary_sample.global.util.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor @Slf4j
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ImageRepository imageRepository;
    public List<ArticleResponseDto> getAllArticles(ArticleSearchDto request) {

        return articleRepository.searchArticles(request).stream().map(ArticleResponseDto::of)
                .collect(Collectors.toList());
    }

    public ArticleResponseDto createArticle(ArticleServiceCreateRequest request) {

        Article article = articleRepository.save(Article.create(request));
        request.getImages().forEach(image -> imageRepository.save(Image.create(image, article)));

        return ArticleResponseDto.of(article);
    }
}
