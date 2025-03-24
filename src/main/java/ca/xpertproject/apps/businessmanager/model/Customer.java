package ca.xpertproject.apps.businessmanager.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="customer")

public class Customer {
	
	
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Customer(Long id, String firstName, String lastName, String address, String city, String phoneNumber,
			String email, String picture, String barcodeValue, List<Subscription> subscriptions) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.picture = picture;
		this.barcodeValue = barcodeValue;
		this.subscriptions = subscriptions;
	}



	@Id
	@GeneratedValue(generator = "customer_id_seq")
	@SequenceGenerator(name="customer_id_seq", sequenceName = "customer_id_seq", allocationSize = 1)
	@Column(name="id")
	public Long id;
	
	@Column(name="firstname")
	public String firstName;
	
	@Column(name="lastname")
	public String lastName;
	
	@Column(name="address")
	public String address;
	
	@Column(name="city")
	public String city;
	
	@Column(name="phonenumber")
	public String phoneNumber;
	
	@Column(name="email")
	public String email;
	
	@Column(name="picture")
	public String picture;
	
	@Column(name="barcodevalue")
	public String barcodeValue;
	
	@OneToMany(mappedBy="customerId")
	public List<Subscription> subscriptions;
	
	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPicture() {
		return picture;
	}



	public void setPicture(String picture) {
		this.picture = picture;
	}



	public String getBarcodeValue() {
		return barcodeValue;
	}



	public void setBarcodeValue(String barcodeValue) {
		this.barcodeValue = barcodeValue;
	}



	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public String toCsvString() {
		// TODO Auto-generated method stub
		return String.format("\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\"", 
				this.id, 
				this.firstName, 
				this.lastName, 
				this.address, 
				this.city, 
				this.phoneNumber, 
				this.email);
	}


}
