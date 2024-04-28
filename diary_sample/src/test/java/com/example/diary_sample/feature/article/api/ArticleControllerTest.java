package com.example.diary_sample.feature.article.api;

import com.example.diary_sample.feature.article.dto.ArticleCreateRequest;
import com.example.diary_sample.feature.article.support.ControllerTestSupport;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {ArticleController.class,
})
class ArticleControllerTest  {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper om;

    @MockBean
    protected ArticleService articleService;

    @DisplayName("게시글을 생성하는 요청을 받아 처리한다.")
    @Test
    void createArticle() throws Exception {
        // given
        LocalDateTime now = LocalDateTime.now();
        ArticleCreateRequest request = ArticleCreateRequest.builder()
                .title("")
                .content("")
                .images(new ArrayList<>())
                .createdAt(now)
                .build();

        // when
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

        // then
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