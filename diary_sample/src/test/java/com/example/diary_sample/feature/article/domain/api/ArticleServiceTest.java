package com.example.diary_sample.feature.article.domain.api;

import com.example.diary_sample.feature.article.api.ArticleService;
import com.example.diary_sample.feature.article.domain.Article;
import com.example.diary_sample.feature.article.domain.ArticleRepository;
import com.example.diary_sample.feature.article.dto.ArticleCreateRequest;
import com.example.diary_sample.feature.article.dto.ArticleResponseDto;
import com.example.diary_sample.feature.article.dto.ArticleSearchDto;
import com.example.diary_sample.global.util.Response;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    ObjectMapper om;

    @DisplayName("내용과 제목을 입력하여 게시글을 생성한다.")
    @Test
    void createArticle() {
        // given
        LocalDateTime now = LocalDateTime.of(2024,4,1,16,0,0);

        ArticleCreateRequest request1 = createTestRequest("111", "222");
        ArticleCreateRequest request2 = createTestRequest("112", "223");
        articleRepository.saveAll(
                List.of(Article.create(request1.toService(now)),
                        Article.create(request2.toService(now))));

        // when
        Response<?> result = articleService.createArticle(request1.toService(now));

        // then
        assertThat(result).isNotNull();
        assertThat(result.getHttpStatus().value()).isEqualTo(200);
        assertThat(result.getData()).extracting(
                "title", "content"
        ).contains("111", "222");
    }

    @DisplayName("조회 가능한 게시물들을 조회한다.")
    @Test
    void getAllArticles() {
        // given
        String title1 = "111";
        String title2 = "222";
        String content1 = "222";
        String content2 = "223";
        LocalDateTime now = LocalDateTime.of(2024,4,1,16,0,0);

        ArticleCreateRequest request1 = createTestRequest(title1, content1);
        ArticleCreateRequest request2 = createTestRequest(title2, content2);
        articleRepository.saveAll(
                List.of(Article.create(request1.toService(now)),
                        Article.create(request2.toService(now))));

        ArticleSearchDto search = ArticleSearchDto.builder().build();

        // when
        Response<?> result = articleService.getAllArticles(search);

        List<ArticleResponseDto> data = om.convertValue(result.getData(), new TypeReference<List<ArticleResponseDto>>(){});

        // then

        assertThat(result.getData()).isNotNull();
        assertThat(result.getStatusNumber()).isEqualTo(200);
        assertThat(data)
                .extracting("title", "content")
                .containsExactlyInAnyOrder(
                        tuple(title1, content1),
                        tuple(title2, content2)
                );
    }

    @DisplayName("제목과 내용으로 검색된 게시물을 조회한다.")
    @Test
    void getArticlesByTitleAndContent() {
        // given
        String title1 = "111";
        String title2 = "222";
        String content1 = "333";
        String content2 = "444";
        LocalDateTime now = LocalDateTime.of(2024,4,1,16,0,0);

        ArticleCreateRequest request1 = createTestRequest(title1, content1);
        ArticleCreateRequest request2 = createTestRequest(title2, content2);
        articleRepository.saveAll(
                List.of(Article.create(request1.toService(now)),
                        Article.create(request2.toService(now))));

        ArticleSearchDto search = ArticleSearchDto.builder()
                .title(title2)
                .content(content2)
                .build();

        // when
        Response<?> result = articleService.getAllArticles(search);
        List<ArticleResponseDto> data = om.convertValue(result.getData(), new TypeReference<List<ArticleResponseDto>>(){});

        // then
        assertThat(result.getData()).isNotNull();
        assertThat(result.getStatusNumber()).isEqualTo(200);
        assertThat(data)
                .extracting("title", "content")
                .containsExactlyInAnyOrder(
                        tuple(title2, content2)
                );
    }


    private ArticleCreateRequest createTestRequest(String title, String content) {
        return ArticleCreateRequest.builder()
                .title(title)
                .content(content)
                .build();
    }

}