package sample.diary.mock

import org.mockito.Mockito
import sample.diary.api.article.ArticleService

object MockService {
    fun getArticleService(
        articleService: ArticleService = Mockito.mock(
            ArticleService::class.java)
    ) = articleService
}