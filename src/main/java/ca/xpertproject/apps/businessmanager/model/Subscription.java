package ca.xpertproject.apps.businessmanager.model;

import java.util.Date;

import org.hibernate.annotations.Columns;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name="subscription")
public class Subscription {

	@Id
	@GeneratedValue(generator = "subscription_id_seq")
	@SequenceGenerator(name="subscription_id_seq", sequenceName = "subscription_id_seq", allocationSize = 1)
	@Column(name="id")
	public Long id;
	
	@Column(name="customer_id",insertable = false, updatable = false)
	public Long customerId;
	
	@ManyToOne
	public Customer customer;
	
	@Column(name="duration")	
	public Integer duration;
	
	@Column(name="taekwondo")
	public boolean taekwondo;

	@Column(name="kickboxing")
	public boolean kickboxing;
	
	@Column(name="taekibodo")
	public boolean taekibodo;
	
	@Column(name="amount")
	public Double amount;
	
	@Column(name="subscription_date")
	public Date subscriptionDate;
	
}
