package sample.diary.dto.article

import lombok.Builder
import lombok.Getter

@Getter @Builder
class ArticleServiceRequest(
    val title: String = "",
    val content: String = "",

){
    data class ArticleServiceRequest(
        var title: String = "",
        var content: String = ""
    )

}