package sample.diary.dto.article

import lombok.Builder
import lombok.Getter

@Getter
class ArticleRequest (
    val title: String,
    val content: String,
        ){
    @Builder
    fun  toService():ArticleServiceRequest{
        return ArticleServiceRequest(this.title, this.content)
    }
}