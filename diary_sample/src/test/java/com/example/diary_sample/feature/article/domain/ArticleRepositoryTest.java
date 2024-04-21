package com.example.diary_sample.feature.article.domain;

import com.example.diary_sample.feature.article.dto.ArticleCreateRequest;
import com.example.diary_sample.feature.article.dto.ArticleSearchDto;
import com.example.diary_sample.feature.article.dto.ArticleServiceCreateRequest;
import org.assertj.core.api.Assertions;
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

    @DisplayName("검색 조건에 충족되는 게시물들을 조회한다.")
    @Test
    void searchArticles() {
        // given
        String title1 = "title1";
        String title2 = "title2";
        String content1 = "content1";
        String content2 = "content2";

        ArticleServiceCreateRequest article1 = createArticles(title1, content1);
        ArticleServiceCreateRequest article2 = createArticles(title2, content2);

        articleRepository.saveAll(List.of(Article.create(article1), Article.create(article2)));

        // when
        ArticleSearchDto search = ArticleSearchDto.builder()
                .title(title1)
                .content(content1)
                .build();
        List<Article> result = articleRepository.searchArticles(search);

        // then
        assertThat(result).hasSize(1);
        assertThat(result).isNotEmpty();
        assertThat(result)
                .extracting("title", "content")
                .containsExactlyInAnyOrder(
                        tuple("title1", "content1")
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