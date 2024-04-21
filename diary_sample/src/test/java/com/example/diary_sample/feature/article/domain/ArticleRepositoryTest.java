package com.example.diary_sample.feature.article.domain;

import com.example.diary_sample.feature.article.dto.ArticleSearchDto;
import com.example.diary_sample.feature.article.dto.ArticleServiceCreateRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

@SpringBootTest
class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;

    @AfterEach
    void tearDown() {
        articleRepository.deleteAllInBatch();
    }

    @DisplayName("검색 조건을 전체로 했을 때 모두 조회한다.")
    @Test
    void searchAllArticles() {
        // given
        String title1 = "title1";
        String title2 = "title2";
        String content1 = "content1";
        String content2 = "content2";

        ArticleServiceCreateRequest article1 = createArticles(title1, content1);
        ArticleServiceCreateRequest article2 = createArticles(title2, content2);

        articleRepository.saveAll(List.of(Article.create(article1), Article.create(article2)));

        ArticleSearchDto search = ArticleSearchDto.builder()
                .build();

        // when
        List<Article> result = articleRepository.searchArticles(search);

        // then
        assertThat(result).hasSize(2);
        assertThat(result).isNotEmpty();
        assertThat(result)
                .extracting("title", "content")
                .containsExactlyInAnyOrder(
                        tuple(title1, content1),
                        tuple(title2, content2)
                );

    }

    @DisplayName("제목과 내용 검색 정보에 충족되는 게시물들을 조회한다.")
    @Test
    void searchArticlesByTitleAndContent() {
        // given
        String title1 = "title1";
        String title2 = "title2";
        String content1 = "content1";
        String content2 = "content2";

        ArticleServiceCreateRequest article1 = createArticles(title1, content1);
        ArticleServiceCreateRequest article2 = createArticles(title2, content2);

        articleRepository.saveAll(List.of(Article.create(article1), Article.create(article2)));

        ArticleSearchDto search = ArticleSearchDto.builder()
                .title(title1)
                .content(content1)
                .build();

        // when
        List<Article> result = articleRepository.searchArticles(search);

        // then
        assertThat(result).hasSize(1);
        assertThat(result).isNotEmpty();
        assertThat(result)
                .extracting("title", "content")
                .containsExactlyInAnyOrder(
                        tuple(title1, content1)
                );
    }



    private ArticleServiceCreateRequest createArticles(String title, String content) {
        return ArticleServiceCreateRequest.builder()
                .title(title)
                .content(content)
                .image(new ArrayList<>())
                .build();

    }

}