package sample.diary.domain.image

import jakarta.persistence.*
import sample.diary.domain.article.Article

@Entity
class Image (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var imageId:Long,
    @ManyToOne(fetch = FetchType.LAZY)
    var article: Article,
)