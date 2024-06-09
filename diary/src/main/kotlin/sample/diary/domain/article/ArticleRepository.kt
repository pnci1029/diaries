package sample.diary.domain.article

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository: JpaRepository<Article, Long>, ArticleSupport {
}