package sample.diary.mock

import org.mockito.Mockito
import sample.diary.domain.article.ArticleRepository

object MockRepository {
    fun getArticleRepository(
        articleRepository: ArticleRepository = Mockito.mock(
            ArticleRepository::class.java)
        ) = articleRepository
}