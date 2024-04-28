package com.example.diary_sample.feature.article.api;

import com.example.diary_sample.feature.article.dto.ArticleCreateRequest;
import com.example.diary_sample.feature.article.dto.ArticleSearchDto;
import com.example.diary_sample.global.util.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @Operation(
            summary = "게시글 생성",
            responses = {
                    @ApiResponse(
                            responseCode = "200"
                    )
            }
    )
    @PostMapping("/create")
    public Response<?> createArticle(@RequestBody @Validated ArticleCreateRequest request) {
        LocalDateTime now = LocalDateTime.now();
        return Response.ok(articleService.createArticle(request.toService(now)));
    }

    @Operation(
            summary = "게시글 목록 조회",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "게시글들을 성공적으로 받아왔을 때",
                            content =
                            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
                    )
            }
    )
    @GetMapping("")
    public Response<?> getAllArticles(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String content,
            @RequestParam(defaultValue = "1999-01-01T00:00") LocalDateTime startDate,
            @RequestParam(defaultValue = "2999-01-01T00:00") LocalDateTime endDate
    ) {
        ArticleSearchDto request = ArticleSearchDto.builder()
                .title(title)
                .content(content)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        return Response.ok(articleService.getAllArticles(request));
    }
}
