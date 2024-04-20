package sample.diary.dto.article

import lombok.Getter
import sample.diary.domain.article.Article

@Getter
class ArticleResponse (
    val articleId: Long,
    val title: String,
    val content: String,
    val view: Int,
        ){
    companion object {
        fun toResponse(article: Article): ArticleResponse {
            return ArticleResponse(article.articleId, article.title, article.content, article.view)
        }
    }
}