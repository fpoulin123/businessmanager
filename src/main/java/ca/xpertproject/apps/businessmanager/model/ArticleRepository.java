package ca.xpertproject.apps.businessmanager.model;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface ArticleRepository extends Repository<Article, Long>{
	
	Article save(Article article);

	Article findById(Long id);
	
	List<Article> findAll();

}
