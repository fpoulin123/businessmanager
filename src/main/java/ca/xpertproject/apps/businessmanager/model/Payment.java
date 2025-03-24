package ca.xpertproject.apps.businessmanager.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="payment")

@NoArgsConstructor
@AllArgsConstructor

@Builder
@Getter
@Setter
public class Payment {
	@Id
	@GeneratedValue(generator = "payment_id_seq")
	@SequenceGenerator(name="payment_id_seq", sequenceName = "payment_id_seq", allocationSize = 1)
	public Long id;
	
	@Column(name="subscription_id",insertable = false, updatable = false)
	public Long subscriptionId;
	
	@Column(name="amount")
	public Double amount;
	
	@Column(name="payment_date")
	public Date paymentDate;
	
	@ManyToOne
	public Subscription subscription;

	public String toCsvString() {
		
		return String.format("\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\"", 
				this.id,
				this.subscriptionId,
				this.subscription.customer.firstName,
				this.subscription.customer.lastName,
				this.amount,
				this.paymentDate
			);
	}
	
}
