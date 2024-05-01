package com.example.diary_sample.feature.article.api;

import com.example.diary_sample.feature.article.dto.ArticleCreateRequest;
import com.example.diary_sample.feature.article.support.ControllerTestSupport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ArticleControllerTest extends ControllerTestSupport {

    @AfterEach
    void tearDown() {

    }

    @DisplayName("게시글을 생성하는 요청을 받아 처리한다.")
    @Test
    void createArticle() throws Exception {
        // given
        LocalDateTime now = LocalDateTime.now();
        ArticleCreateRequest request = ArticleCreateRequest.builder()
                .title("title")
                .content("content")
                .images(List.of("1","2"))
                .createdAt(now)
                .build();

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/article/create")
                        .content(om.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.httpStatus").value("OK"))
                .andExpect(jsonPath("$.statusNumber").value("200"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty())
        ;
    }

    @DisplayName("게시글 생성 요청 시 이미지가 포함되어있지 않으면 예외처리를 한다.")
    @Test
    void createArticleWithoutImage() throws Exception {
        // given
        LocalDateTime now = LocalDateTime.now();
        ArticleCreateRequest request = ArticleCreateRequest.builder()
                .title("title")
                .content("content")
                .images(null)
                .createdAt(now)
                .build();

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/article/create")
                        .content(om.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusNumber").value(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("이미지 속성은 필수입니다."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty())
        ;
    }

    @DisplayName("게시글 생성 시 제목을 기입하지 않으면 예외 처리를 한다.")
    @Test
    void createArticleWithoutText() throws Exception {
        // given
        LocalDateTime now = LocalDateTime.now();
        ArticleCreateRequest request = ArticleCreateRequest.builder()
                .title("")
                .content("content")
                .images(List.of("image1","image2"))
                .createdAt(now)
                .build();

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/article/create")
                        .content(om.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusNumber").value(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("제목 기입은 필수입니다."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty())
        ;
    }

    private ArticleCreateRequest createArticleSupport(
            String title, String content, String imageName, LocalDateTime createdAt
            ) {
        return ArticleCreateRequest.builder()
                .title(title)
                .content(content)
                .createdAt(createdAt)
                .images(List.of(imageName))
                .build();
    }

}