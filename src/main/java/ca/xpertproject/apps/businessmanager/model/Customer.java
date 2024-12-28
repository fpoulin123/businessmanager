package ca.xpertproject.apps.businessmanager.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="customer")

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	
	@Id
	@GeneratedValue(generator = "customer_id_seq")
	@SequenceGenerator(name="customer_id_seq", sequenceName = "customer_id_seq", allocationSize = 1)
	Long id;
	
	@Column(name="firstname")
	String firstName;
	
	@Column(name="lastname")
	String lastName;
	
	String address;
	
	String city;
	
	@Column(name="phonenumber")
	String phoneNumber;
	
	String email;
	
	String course;
	
	String picture;
	
	@Column(name="barcodevalue")
	String barcodeValue;
	
	@Column(name="inscriptiondate")
	Date inscriptionDate;
	

}
