package ca.xpertproject.apps.businessmanager.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="subscription")
public class Subscription {
	
		public Subscription() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Subscription(Long id, Long customerId, Customer customer, Integer duration, Boolean taekwondo,
			Boolean kickboxing, Boolean taekibodo, Double amount, Date subscriptionDate) {
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
	public Boolean taekwondo;

	@Column(name="kickboxing")
	public Boolean kickboxing;
	
	@Column(name="taekibodo")
	public Boolean taekibodo;
	
	@Column(name="amount")
	public Double amount;
	
	@Column(name="subscription_date")
	public Date subscriptionDate;
	
	@OneToMany(mappedBy="subscriptionId")
	public List<Payment> paymentList;
	
	
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



	public Boolean isTaekwondo() {
		return taekwondo;
	}



	public void setTaekwondo(Boolean taekwondo) {
		this.taekwondo = taekwondo;
	}



	public Boolean isKickboxing() {
		return kickboxing;
	}



	public void setKickboxing(Boolean kickboxing) {
		this.kickboxing = kickboxing;
	}



	public Boolean isTaekibodo() {
		return taekibodo;
	}



	public void setTaekibodo(Boolean taekibodo) {
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
	
	

	public List<Payment> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<Payment> paymentList) {
		this.paymentList = paymentList;
	}

	public String toString() {
		
		return String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s", this.id, this.customer.firstName, this.customer.lastName, this.duration, this.taekwondo, this.kickboxing,this.taekibodo, this.amount, this.subscriptionDate);
	}
	
	public String toCsvString() {
		
		return String.format("\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\"", 
				this.id, 
				this.customer.firstName, 
				this.customer.lastName, 
				this.duration, 
				(this.taekwondo)?"Taekwondo":"", 
				(this.kickboxing)?"Kickboxing":"",
				(this.taekibodo)?"Taekibodo":"", 
				this.amount, 
				this.subscriptionDate);
	}
}
