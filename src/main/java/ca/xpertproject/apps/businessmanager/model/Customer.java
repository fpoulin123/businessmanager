package ca.xpertproject.apps.businessmanager.model;

import java.sql.Date;

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


@Entity
@Table(name="customer")

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

@Builder
public class Customer {
	
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
	
	@Column(name="course")
	public String course;
	
	@Column(name="picture")
	public String picture;
	
	@Column(name="barcodevalue")
	public String barcodeValue;
	
	@Column(name="inscriptiondate")
	public Date inscriptionDate;
	

}
