package site.amers.server.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import site.amers.server.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    /**
     * @param startDate
     * @param endDate
     * @return Articles created between startDate and endDate
     */
    List<Article> findByDateCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);
}
