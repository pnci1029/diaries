package sample.diary.domain.article

interface ArticleSupport {
    fun getArticles(): MutableList<Article>
}