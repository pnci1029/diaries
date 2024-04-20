package com.example.diary.feature.article.api;

import com.example.diary.feature.article.domain.Article;
import com.example.diary.feature.article.domain.ArticleRepository;
import com.example.diary.feature.article.dto.ArticleCreateRequest;
import com.example.diary.global.util.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleRepository articleRepository;


    @DisplayName("내용과 제목을 입력하여 게시글을 생성한다.")
    @Test
    void createArticle() {
        // given
        ArticleCreateRequest request1 = createTestRequest("111", "222");
        ArticleCreateRequest request2 = createTestRequest("112", "222");
        articleRepository.saveAll(
                List.of(Article.create(request1.toService()),
                        Article.create(request2.toService())));

        // when
        Response<?> result = articleService.createArticle(request1.toService());

        // then
        assertThat(result).isNotNull();
        assertThat(result.getHttpStatus().value()).isEqualTo(200);
        assertThat(result.getData()).extracting(
                "title", "content"
        ).contains("title 111", "content 222");
    }

    @DisplayName("조회 가능한 게시물들을 조회한다.")
    @Test
    void getAllArticles() {
        // given

        // when
//        Response<?> allArticles = articleService.getAllArticles();

        // then
    }


    private ArticleCreateRequest createTestRequest(String title, String content) {
        return ArticleCreateRequest.builder()
                .title("title " + title)
                .content("content " + content)
                .build();
    }

}