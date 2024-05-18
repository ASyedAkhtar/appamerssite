package site.amers.server.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import site.amers.server.entity.Article;
import site.amers.server.repository.ArticleRepository;

@RestController
@RequestMapping("/api")
public class ArticleController {

    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleRepository repository;

    public ArticleController(ArticleRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/articles")
    List<Article> getArticles() {
        return repository.findAll();
    }

    @GetMapping("hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
}
