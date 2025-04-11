package ca.xpertproject.apps.businessmanager.objects;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class SubscriptionExt {

	public SubscriptionExt() {
		super();
	}

	public SubscriptionExt(Long id, Long customerId, String customerFirstname, String customerLastname,
			Integer duration, Boolean taekwondo, Boolean kickboxing, Boolean taekibodo, Double amount,
			Date subscriptionDate, Date expirationDate, Boolean isValid) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.customerFirstname = customerFirstname;
		this.customerLastname = customerLastname;
		this.duration = duration;
		this.taekwondo = taekwondo;
		this.kickboxing = kickboxing;
		this.taekibodo = taekibodo;
		this.amount = amount;
		this.subscriptionDate = subscriptionDate;
		this.expirationDate = expirationDate;
		this.isValid = isValid;

	}


	private Long id;

	private Long customerId;

	private String customerFirstname;

	private String customerLastname;

	private Integer duration;

	private Boolean taekwondo;

	private Boolean kickboxing;

	private Boolean taekibodo;

	private Double amount;

	private Date subscriptionDate;

	private Date expirationDate;

	private Boolean isValid;

	private Double balance;

	private Boolean payed;

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

	public String getCustomerFirstname() {
		return customerFirstname;
	}

	public void setCustomerFirstname(String customerFirstname) {
		this.customerFirstname = customerFirstname;
	}

	public String getCustomerLastname() {
		return customerLastname;
	}

	public void setCustomerLastname(String customerLastname) {
		this.customerLastname = customerLastname;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Boolean getTaekwondo() {
		return taekwondo;
	}

	public void setTaekwondo(Boolean taekwondo) {
		this.taekwondo = taekwondo;
	}

	public Boolean getKickboxing() {
		return kickboxing;
	}

	public void setKickboxing(Boolean kickboxing) {
		this.kickboxing = kickboxing;
	}

	public Boolean getTaekibodo() {
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

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Boolean getPayed() {
		return payed;
	}

	public void setPayed(Boolean payed) {
		this.payed = payed;
	}

	public String toCsvString() {

		return String.format("\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\"", 
				this.id,
				this.customerId,
				this.customerFirstname,
				this.customerLastname,
				this.duration,
				(this.taekwondo)?"Taekwondo":"", 
				(this.kickboxing)?"Kickboxing":"",
				(this.taekibodo)?"Taekibodo":"", 
				this.amount,
				this.subscriptionDate,
				this.expirationDate,
				this.isValid);

	}



}
