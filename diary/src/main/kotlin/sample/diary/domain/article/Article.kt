package sample.diary.domain.article

import jakarta.persistence.*
import sample.diary.domain.image.Image
import sample.diary.dto.article.ArticleServiceRequest

@Entity
class Article (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val articleId: Long,
    val title: String,
    val content: String,
    val view: Int,

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    var imageList: MutableList<Image>
){
    constructor() : this(0,"", "", 0, mutableListOf())

    companion object {
        fun create(request: ArticleServiceRequest): Article {
            return Article(
                articleId = 0,
                title = request.title,
                content = request.content,
                view = 0,
                imageList = mutableListOf()
            )
        }
    }

}