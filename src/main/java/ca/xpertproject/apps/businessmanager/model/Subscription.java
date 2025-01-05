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

@Entity
@Table(name="subscription")
public class Subscription {
	
		public Subscription() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Subscription(Long id, Long customerId, Customer customer, Integer duration, boolean taekwondo,
			boolean kickboxing, boolean taekibodo, Double amount, Date subscriptionDate) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.customer = customer;
		this.duration = duration;
		this.taekwondo = taekwondo;
		this.kickboxing = kickboxing;
		this.taekibodo = taekibodo;
		this.amount = amount;
		this.subscriptionDate = subscriptionDate;
	}



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
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public Long getCustomerId() {
		return customerId;
	}



	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}



	public Customer getCustomer() {
		return customer;
	}



	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	public Integer getDuration() {
		return duration;
	}



	public void setDuration(Integer duration) {
		this.duration = duration;
	}



	public boolean isTaekwondo() {
		return taekwondo;
	}



	public void setTaekwondo(boolean taekwondo) {
		this.taekwondo = taekwondo;
	}



	public boolean isKickboxing() {
		return kickboxing;
	}



	public void setKickboxing(boolean kickboxing) {
		this.kickboxing = kickboxing;
	}



	public boolean isTaekibodo() {
		return taekibodo;
	}



	public void setTaekibodo(boolean taekibodo) {
		this.taekibodo = taekibodo;
	}



	public Double getAmount() {
		return amount;
	}



	public void setAmount(Double amount) {
		this.amount = amount;
	}



	public Date getSubscriptionDate() {
		return subscriptionDate;
	}



	public void setSubscriptionDate(Date subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}

	public boolean isValid() {
		
		long subscriptionDateTime = this.getSubscriptionDate().getTime();
		
		int duration = this.getDuration();
		
		long durationLong = Integer.toUnsignedLong(duration);
					
		long dayInMillis = 24*3600*1000;
		
		long durationDateTime = durationLong*dayInMillis;
		
		System.out.println("Duration : " + duration);
		
		long expirationDateTime = subscriptionDateTime + durationDateTime;
		
		Date expirationDate = new Date(expirationDateTime);
		
		return expirationDate.after(new Date());
		
	}


	public String toString() {
		
		return String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s", this.id, this.customer.firstName, this.customer.lastName, this.duration, this.taekwondo, this.kickboxing,this.taekibodo, this.amount, this.subscriptionDate);
	}
	
}
