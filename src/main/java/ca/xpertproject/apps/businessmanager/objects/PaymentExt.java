package ca.xpertproject.apps.businessmanager.objects;

import ca.xpertproject.apps.businessmanager.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

public class PaymentExt extends Payment{
	
	String customerFirstName;
	String customerLastName;
	Long subscriptionNumber;
	

}
