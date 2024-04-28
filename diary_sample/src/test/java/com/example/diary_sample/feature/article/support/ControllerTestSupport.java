package com.example.diary_sample.feature.article.support;

import com.example.diary_sample.feature.article.api.ArticleController;
import com.example.diary_sample.feature.article.api.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {ArticleController.class,
        })
public abstract class ControllerTestSupport {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper om;

    @MockBean
    protected ArticleService articleService;
}
