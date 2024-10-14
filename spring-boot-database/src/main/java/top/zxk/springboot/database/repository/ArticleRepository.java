package top.zxk.springboot.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.zxk.springboot.database.entity.Article;

public interface ArticleRepository extends JpaRepository<Article,Long> {

}
