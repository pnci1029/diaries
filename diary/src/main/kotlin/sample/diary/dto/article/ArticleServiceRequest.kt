package sample.diary.dto.article


class ArticleServiceRequest(
    val title: String = "",
    val content: String = "",

){
    data class ArticleServiceRequest(
        var title: String = "",
        var content: String = ""
    )

}