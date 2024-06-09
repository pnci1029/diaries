package sample.diary.dto.article

class ArticleRequest (
    val title: String,
    val content: String,
        ){
    fun  toService():ArticleServiceRequest{
        return ArticleServiceRequest(this.title, this.content)
    }
}