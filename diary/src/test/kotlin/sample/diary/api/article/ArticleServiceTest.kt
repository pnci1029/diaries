//package sample.diary.api.article
//
//import org.assertj.core.api.AssertionsForClassTypes.assertThat
//import org.assertj.core.api.AssertionsForClassTypes.tuple
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.extension.ExtendWith
//import org.mockito.MockitoAnnotations
//import org.mockito.junit.jupiter.MockitoExtension
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.http.HttpStatus
//import sample.diary.dto.article.ArticleServiceRequest
//import sample.diary.mock.MockRepository
//import sample.diary.mock.MockService
//
//// given
//// when
//// then
//@ExtendWith(MockitoExtension::class)
//@SpringBootTest
//internal class ArticleServiceTest{
//    private var articleService= MockService.getArticleService()
//    private var articleRepository= MockRepository.getArticleRepository()
//
//
//    @BeforeEach
//    fun beforeEach() {
//        MockitoAnnotations.initMocks(this)
//    }
//
//
//    @Test
//    fun createArticle() {
//        // given
//        val title1 = "title1"
//        val title2 = "title2"
//        val content1 = "content1"
//        val content2 = "content2"
//
//        val request1 = createArticles(title1, content1)
//        val request2 = createArticles(title2, content2)
//
//        // when
//        val result = articleService.createArticle(request1)
//
//        // then
//        assertThat(result.statusCode).isEqualTo(HttpStatus.OK)
//        assertThat(result.body).extracting("title","content")
//            .containsExactlyInAnyOrder(
//            tuple(title2, content2)
//        );
//    }
//
//    fun createArticles(title : String, content: String): ArticleServiceRequest {
//        return ArticleServiceRequest(
//            title = title,
//            content = content
//        )
//    }
//
//}