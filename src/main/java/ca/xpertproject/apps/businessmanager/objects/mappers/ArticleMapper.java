package ca.xpertproject.apps.businessmanager.objects.mappers;

import ca.xpertproject.apps.businessmanager.model.Article;
import ca.xpertproject.apps.businessmanager.objects.ACObject;

public class ArticleMapper {


	public static ACObject convertForList(Article article) {
		ACObject acObject = new ACObject();
		
		acObject.setLabel(article.getName());
		
		acObject.setValue(article.getId().toString());

		return acObject;
	}

}
