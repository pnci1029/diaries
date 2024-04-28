package sample.diary.mock

import org.mockito.Mockito
import sample.diary.api.article.ArticleService
import sample.diary.domain.article.ArticleRepository
import sample.diary.mock.MockRepository.getArticleRepository

object MockService {
    fun getArticleService(
        articleRepository: ArticleRepository = getArticleRepository(),
    ) = ArticleService(
        articleRepository = articleRepository,
    )
}