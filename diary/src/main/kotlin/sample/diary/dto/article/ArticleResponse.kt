package sample.diary.dto.article

import sample.diary.domain.article.Article


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