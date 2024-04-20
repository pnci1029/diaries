package sample.diary.api.article

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import sample.diary.domain.article.Article
import sample.diary.domain.article.ArticleRepository
import sample.diary.dto.article.ArticleResponse
import sample.diary.dto.article.ArticleServiceRequest

@Service @RequiredArgsConstructor
class ArticleService (
    val articleRepository: ArticleRepository
        ){
    fun createArticle(request: ArticleServiceRequest) : ResponseEntity<ArticleResponse> {
        val article = articleRepository.save(Article.create(request))
        return ResponseEntity.ok(ArticleResponse.toResponse(article))
    }
}