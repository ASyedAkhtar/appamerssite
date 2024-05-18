package site.amers.server.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import site.amers.server.entity.Article;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository repository;

    @Test
    @Order(11)
    public void insertOneArticle() {
        // Create article
        Article article = new Article("Inserted Title", "Inserted content.");
        // Save article
        System.out.println("INSERTED " + repository.save(article).toString());
    }
    
    @ParameterizedTest
    @Order(12)
    @ValueSource(longs = {1})
    public void updateOneArticle(Long id) {
        try {
            // Find article
            Article article = repository.findById(id).get();
            // Update article
            article.setTitle(("Updated Title <= " + article.getTitle()).substring(0, 20));
            article.setContent("Updated content. <= " + article.getContent());
            // Save article
            System.out.println("UPDATED " + repository.save(article).toString());
        } catch (NoSuchElementException nsee) {
            System.out.println("Please insert Article with ID 1 to pursue this test.");
            System.out.println("INSERT INTO amerssite.article VALUES (1, 'Inserted Title', 'Inserted Content.', CURRENT_DATE, CURRENT_DATE);");
        }
    }

    @ParameterizedTest
    @Order(13)
    @ValueSource(longs = {1})
    public void selectOneArticle(Long id) {
        try {
            System.out.println("SELECTED " + repository.findById(id).get().toString());
        } catch (NoSuchElementException nsee) {
            System.out.println("Please insert Article with ID 1 to pursue this test.");
            System.out.println("INSERT INTO amerssite.article VALUES (1, 'Inserted Title', 'Inserted Content.', CURRENT_DATE, CURRENT_DATE);");
        }
    }

    @ParameterizedTest
    @Order(14)
    @ValueSource(ints = {3})
    public void insertManyArticles(Integer quantity) {
        List<Article> articles = new ArrayList<Article>(quantity);
        for (int i = 0; i < quantity; i++) {
            articles.add(new Article("Inserted Title " + (i + 1), "Inserted content " + (i + 1) + "."));
        }
        // Save articles
        System.out.println("INSERTED ALL " + repository.saveAll(articles).stream().map(Article::toString).collect(Collectors.joining(", ")));
    }

    @Test
    @Order(15)
    public void selectManyArticles() {
        System.out.println("SELECTED ALL " + repository.findAll().stream().map(Article::toString).collect(Collectors.joining(", ")));
    }

    @ParameterizedTest
    @Order(16)
    @ValueSource(longs = {1})
    public void deleteOneArticle(Long id) {
        try {
            Article article = repository.findById(id).get();
            repository.delete(article);
            System.out.println("DELETED " + article);
        } catch (NoSuchElementException nsee) {
            System.out.println("Please insert Article with ID 1 to pursue this test.");
            System.out.println("INSERT INTO amerssite.article VALUES (1, 'Inserted Title', 'Inserted Content.', CURRENT_DATE, CURRENT_DATE);");
        }
    }
}
