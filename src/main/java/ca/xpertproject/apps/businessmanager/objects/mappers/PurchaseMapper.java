package ca.xpertproject.apps.businessmanager.objects.mappers;

import ca.xpertproject.apps.businessmanager.model.GenericBuilder;
import ca.xpertproject.apps.businessmanager.model.Purchase;
import ca.xpertproject.apps.businessmanager.objects.PurchaseExt;

public class PurchaseMapper {
	
	public static PurchaseExt convert(Purchase purchase) {
		
		PurchaseExt purchaseExt = GenericBuilder.of(PurchaseExt::new)
				.with(PurchaseExt::setId, purchase.getId())
				.with(PurchaseExt::setCustomerId, purchase.getCustomerId())
				.with(PurchaseExt::setCustomer, purchase.getCustomer())
				.with(PurchaseExt::setArticleId, purchase.getArticleId())
				.with(PurchaseExt::setArticle, purchase.getArticle())
				.with(PurchaseExt::setNumber, purchase.getNumber())
				.with(PurchaseExt::setDiscount, purchase.getDiscount())
				.with(PurchaseExt::setPurchaseDate, purchase.getPurchaseDate())
				.with(PurchaseExt::setCustomerFirstName, purchase.getCustomer().getFirstName())
				.with(PurchaseExt::setCustomerLastName, purchase.getCustomer().getLastName())
				.with(PurchaseExt::setArticleName, purchase.getArticle().getName())
				.with(PurchaseExt::setArticlePrice, purchase.getArticle().getPrice())
				.with(PurchaseExt::setPurchaseAmount, getPurchaseAmount(purchase))
				.build();
				
				
		return purchaseExt;
		
	}

	private static Double getPurchaseAmount(Purchase purchase) {
		
		return purchase.getNumber()*purchase.getArticle().getPrice();
	}

}
