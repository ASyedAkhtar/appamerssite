package site.amers.server.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import site.amers.server.entity.Article;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ArticleRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(ArticleRepositoryTest.class);

    @Autowired
    private ArticleRepository repository;

    @Test
    @Order(10)
    public void testInsertOneArticle() {
        log.info("testInsertOneArticle");
        // Create article
        Article article = new Article("Inserted Title", "Inserted content.");
        // Save article
        log.info("INSERTED " + repository.save(article).toString());
    }
    
    @ParameterizedTest
    @Order(20)
    @ValueSource(longs = {1})
    public void testUpdateOneArticle(Long id) {
        log.info("testUpdateOneArticle");
        if (repository.existsById(id)) {
            // Find article
            Article article = repository.findById(id).get();
            // Update article
            article.setTitle(("Updated Title <= " + article.getTitle()).substring(0, 20));
            article.setContent("Updated content. <= " + article.getContent());
            // Save article
            log.info("UPDATED " + repository.save(article).toString());
        } else {
            log.info("Please insert Article with ID 1 to pursue this test.\n" 
                + "INSERT INTO amerssite.article VALUES (1, 'Inserted Title', 'Inserted Content.', "
                + "CURRENT_DATE, CURRENT_DATE);");
        }
    }

    @ParameterizedTest
    @Order(30)
    @ValueSource(longs = {1})
    public void testSelectOneArticle(Long id) {
        log.info("testSelectOneArticle");
        if (repository.existsById(id)) {
            log.info("SELECTED " + repository.findById(id).get().toString());
        } else {
            log.info("Please insert Article with ID 1 to pursue this test.\n" 
                + "INSERT INTO amerssite.article VALUES (1, 'Inserted Title', 'Inserted Content.', "
                + "CURRENT_DATE, CURRENT_DATE);");
        }
    }

    @ParameterizedTest
    @Order(40)
    @ValueSource(ints = {3})
    public void testInsertManyArticles(Integer quantity) {
        log.info("testInsertManyArticles");
        List<Article> articles = new ArrayList<Article>(quantity);
        for (int i = 0; i < quantity; i++) {
            articles.add(new Article("Inserted Title " + (i + 1), "Inserted content " + (i + 1) + "."));
        }
        // Save articles
        log.info("INSERTED ALL " + repository.saveAll(articles).stream().map(Article::toString).collect(
            Collectors.joining("\n")
        ));
    }

    @Test
    @Order(50)
    public void testCountArticles() {
        log.info("testCountArticles");
        log.info("COUNT " + repository.count());
    }

    @Test
    @Order(60)
    public void testSelectManyArticles() {
        log.info("testSelectManyArticles");
        log.info("SELECTED ALL " + repository.findAll().stream().map(Article::toString).collect(
            Collectors.joining("\n")
        ));
    }

    @ParameterizedTest
    @Order(65)
    @ValueSource(longs = {30})
    @Transactional
    public void testSelectManyArticlesCreatedBetween(Long numberDays) {
        log.info("testSelectManyArticlesCreatedBetween");
        log.info("SELECTED ALL CREATED BETWEEN " + repository.findByDateCreatedBetween(
            LocalDateTime.now().minusDays(numberDays), LocalDateTime.now()
        ).stream().map(Article::toString).collect(Collectors.joining("\n")));
    }

    @Test
    @Order(68)
    public void testSelectManyArticlesOrderByDateCreatedDesc() {
        log.info("testSelectManyArticlesOrderByDateCreatedDesc");
        Sort orderBy = Sort.by("dateCreated").descending().and(Sort.by("id").ascending());
        List<Article> articles = repository.findAll(orderBy);
        log.info("SELECTED ALL ORDERED BY DATE CREATED " + articles.stream().map(Article::toString).collect(
            Collectors.joining("\n")
        ));
    }

    @ParameterizedTest
    @Order(70)
    @ValueSource(longs = {1})
    public void testDeleteOneArticle(Long id) {
        log.info("testDeleteOneArticle");
        if (repository.existsById(id)) {
            Article article = repository.findById(id).get();
            repository.delete(article);
            log.info("DELETED " + article);
        } else {
            log.info("Please insert Article with ID 1 to pursue this test.\n" 
                + "INSERT INTO amerssite.article VALUES (1, 'Inserted Title', 'Inserted Content.', "
                + "CURRENT_DATE, CURRENT_DATE);");
        }
    }

    @Disabled
    @Test
    @Order(80)
    public void testDeleteManyArticles() {
        log.info("testDeleteManyArticles");
        List<Article> articles = repository.findAll();
        repository.deleteAll(articles);
        log.info("DELETED ALL " + articles.stream().map(Article::toString).collect(Collectors.joining("\n")));
    }
}
