package ca.xpertproject.apps.businessmanager.objects;

import ca.xpertproject.apps.businessmanager.model.Purchase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

public class PurchaseExt extends Purchase{
	
	private String customerFirstName;
	private String customerLastName;
	private String articleName;
	private Double articlePrice;
	private Double purchaseAmount;

}
