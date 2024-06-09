package sample.diary.api.article

import org.assertj.core.api.AssertionsForClassTypes.tuple
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import sample.diary.domain.article.Article
import sample.diary.domain.article.ArticleRepository
import sample.diary.dto.article.ArticleServiceRequest
import org.assertj.core.api.Assertions.assertThat;

// given
// when
// then
@ExtendWith(MockitoExtension::class)
@SpringBootTest
class ArticleServiceTest{
//    private val articleRepository= getArticleRepository()
//    private val articleService= getArticleService(
//        articleRepository = articleRepository
//    )
    @BeforeEach
    fun beforeEach() {
        MockitoAnnotations.initMocks(this)
    }

    @Autowired
    private lateinit var articleService: ArticleService

    @Autowired
    private lateinit var articleRepository: ArticleRepository

    @Test
    @DisplayName("요청 정보를 받아서 게시물을 생성한다.")
    fun createArticle() {
        // given
        val title1 = "title1"
        val title2 = "title2"
        val content1 = "content1"
        val content2 = "content2"

        val request1 = createArticles(title1, content1)
        val request2 = createArticles(title2, content2)

        // when
        val result = articleService.createArticle(request1)

        // then
        assertThat(result.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(result.body).extracting("title","content")
            .contains(
                title1, content1
            )
    }

    @Test
    @DisplayName("전체 게시물을 조회한다.")
    fun getAllArticles() {
        //given
        val title1 = "title1"
        val content1 = "content1"
        val title2 = "title2"
        val content2 = "content2"
        val title3 = "title3"
        val content3 = "content3"

        val request1 = createArticles(title1, content1)
        val request2 = createArticles(title2, content2)
        val request3 = createArticles(title3, content3)

        val articleList = listOf(
            Article.create(request1), Article.create(request2), Article.create(request3)
        )

        articleRepository.saveAll(articleList)

        //when
        val result = articleService.getArticles()

        //then
        assertThat(result.size).isEqualTo(articleList.size)
        assertThat(result).isInstanceOf(MutableList::class.java)

        assertThat(result)
            .extracting("title", "content")
            .containsExactlyInAnyOrder(
                tuple(title1, content1),
                tuple(title2, content2),
                tuple(title3, content3)
            )
    }

    fun createArticles(title : String, content: String): ArticleServiceRequest {
        return ArticleServiceRequest(
            title = title,
            content = content,
        )
    }

}