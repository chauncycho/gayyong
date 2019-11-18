package my.app.gayyong.repository;

import my.app.gayyong.entiity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Integer> {
}
