package sample.diary.domain.article

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository: CrudRepository<Article, Long>, ArticleSupport {
}