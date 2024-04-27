package sample.diary.domain.article

import org.springframework.data.repository.CrudRepository

interface ArticleRepository: CrudRepository<Article, Long> {
}