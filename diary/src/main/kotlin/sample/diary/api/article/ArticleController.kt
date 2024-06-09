package sample.diary.api.article

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sample.diary.domain.article.Article
import sample.diary.dto.article.ArticleRequest
import sample.diary.dto.article.ArticleResponse

@RestController
@RequestMapping("/api/article")
class ArticleController (
//    val articleDtoMapper: ArticleDtoMapper,
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
    fun getArticles():MutableList<Article> {
        return articleService.getArticles()
//        val toDto = articleDtoMapper.toSummaryDTO("11","22")
    }
}