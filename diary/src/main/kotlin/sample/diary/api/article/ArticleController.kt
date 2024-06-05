package sample.diary.api.article

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sample.diary.dto.article.ArticleRequest
import sample.diary.dto.article.ArticleResponse

@RestController @RequiredArgsConstructor
@RequestMapping("/api/article")
class ArticleController (
    val articleService: ArticleService
        ){

    @PostMapping("/create")
    @Operation(summary = "게시글 생성", responses = [ApiResponse(
        responseCode = "200",
        content = [
            Content(schema = Schema(implementation = ArticleResponse::class))
        ]
    )])
    fun createArticle(@RequestBody request: ArticleRequest): ResponseEntity<ArticleResponse> {
        return articleService.createArticle(request.toService())
    }

    @GetMapping()
    @Operation(
        summary = "게시글 전체 조회", responses = [ApiResponse(
            responseCode = "200",
            content = [
                Content(schema = Schema(implementation = ArticleResponse::class))
            ]
        )]
    )
        // TODO: 검색 파라미터
    fun getArticles() {
        articleService.getArticles()

    }
}