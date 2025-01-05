package site.amers.server.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import site.amers.server.entity.Article;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ArticleLoadRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(ArticleLoadRepositoryTest.class);

    @Autowired
    private ArticleRepository repository;

    @Test
    @Order(180)
    public void testDeleteManyLoadArticles() {
        log.info("testDeleteManyLoadArticles");
        List<Article> articles = repository.findAll();
        repository.deleteAll(articles);
        log.info("DELETED ALL " + articles.stream().map(Article::toString).collect(Collectors.joining("\n")));
    }

    @Test
    @Order(190)
    public void testInsertManyLoadArticles() {
        log.info("testInsertManyLoadArticles");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Article> articles = objectMapper.readValue(ResourceUtils.getFile("classpath:article-backup.json"), 
                new TypeReference<List<Article>>(){});

            // Save articles
            log.info("INSERTED ALL " + repository.saveAll(articles).stream().map(Article::toString).collect(
                Collectors.joining("\n")
            ));
        } catch (Exception e) {
            log.error("testInsertManyLoadArticles failed to load articles from article-backup.json!");
        }
    }
}
