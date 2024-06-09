package sample.diary.domain.article

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import sample.diary.domain.article.QArticle.article

@Repository
class ArticleSupportImpl(
    private val queryFactory: JPAQueryFactory
) :ArticleSupport{

    override fun getArticles(): MutableList<Article> {
        val result = queryFactory
            .selectFrom(article)
            .fetch()

//        return Article.create(ArticleServiceRequest("title","content"))
        return result
    }
}