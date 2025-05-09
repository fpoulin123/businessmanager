package ca.xpertproject.apps.businessmanager.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;


@Entity
@Table(name="customer")

public class Customer implements CSVTransformable{
		
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Customer(Long id, String firstName, String lastName, String address, String city, String phoneNumber,
			String email, String picture, String barcodeValue, List<Subscription> subscriptions, String title,
			String level, Integer height, Integer weight, Date birthdate, boolean active) {
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
		this.title = title;
		this.level = level;
		this.height = height;
		this.weight = weight;
		this.birthdate = birthdate;
		this.active = active;
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
	
	@Column(name="title")
	public String title;
	
	@Column(name="level")
	public String level;
	
	@Column(name="height")
	public Integer height;
	
	@Column(name="weight")
	public Integer weight;
	
	@Column(name="birthdate")
	public Date birthdate;
	
	@Transient
	public Integer age;
	
	@Column(name="active")
	public boolean active;
	
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
	
	

	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	public Integer getHeight() {
		return height;
	}
	
	public void setHeight(Integer height) {
		this.height=height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Integer getAge() {
		Date birthDate = getBirthdate();
		Integer age = null;
		
		if(birthDate!=null) {
			try {
				SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date now = new Date();
				String currentYearStr = yearFormat.format(now);
				
				String birthYearStr =  yearFormat.format(birthDate);
				Integer currentYear = Integer.parseInt(currentYearStr);
				Integer birthYear = Integer.parseInt(birthYearStr);
				
				String birthDateStr = sdf.format(birthDate);
				
				String comparingDateStr = birthDateStr.replace(birthYearStr, currentYearStr);
				
				Date comparingDate = sdf.parse(comparingDateStr);
				
				int numYear = currentYear - birthYear;
				
				if(comparingDate.after(now)) {
					numYear = numYear - 1;
				}
				
				age = numYear;
				
			}catch (Exception e) {
				
			}
		}
		
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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
				this.email,
				this.title,
				this.level);
	}


}
