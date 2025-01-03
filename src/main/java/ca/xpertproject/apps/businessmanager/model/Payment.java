package ca.xpertproject.apps.businessmanager.model;

import java.util.Date;

import org.hibernate.annotations.Columns;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder

@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="payment")
public class Payment {

	@Id
	@GeneratedValue(generator = "payment_id_seq")
	@SequenceGenerator(name="payment_id_seq", sequenceName = "payment_id_seq", allocationSize = 1)
	@Column(name="id")
	public Long id;
	
	@Column(name="customer_id")
	public Long customerId;
	
	@Column(name="product")	
	public Integer product;
	
	@Column(name="taekwondo")
	public boolean taekwondo;

	@Column(name="kickboxing")
	public boolean kickboxing;
	
	@Column(name="taekibodo")
	public boolean taekibodo;
	
	@Column(name="amount")
	public Double amount;
	
	@Column(name="payed_amount")
	public Double payedAmount;
	
	@Column(name="payment_date")
	public Date paymentDate;
	
}
