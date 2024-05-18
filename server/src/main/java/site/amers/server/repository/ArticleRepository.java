package site.amers.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import site.amers.server.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
