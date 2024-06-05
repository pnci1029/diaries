package sample.diary.domain.article

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import sample.diary.dto.article.ArticleRequest
import sample.diary.dto.article.ArticleServiceRequest

@Repository
class ArticleSupportImpl(
    private val queryFactory: JPAQueryFactory
) :ArticleSupport{

    override fun getArticles(): Article {
//        QArticle
        return Article.create(ArticleServiceRequest("title","content"))
    }
}