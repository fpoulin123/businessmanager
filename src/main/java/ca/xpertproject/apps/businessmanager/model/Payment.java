package ca.xpertproject.apps.businessmanager.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
	
	@Column(name="subscription_id")
	public Long subscriptionId;
	
	@Column(name="amount")
	public Double amount;
	
	@Column(name="payment_date")
	public Date paymentDate;
}
