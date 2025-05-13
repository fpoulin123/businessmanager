package ca.xpertproject.apps.businessmanager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "event_attendee")

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

@Builder

public class EventAttendee {

	@Id
	@GeneratedValue(generator = "event_attendee_id_seq")
	@SequenceGenerator(name="event_attendee_id_seq", sequenceName = "event_attendee_id_seq", allocationSize = 1)
	@Column(name="id")
	Long id;
	
	@Column(name="event_id")
	Long eventId;
	
	@ManyToOne
	Event event;
	
	
	@Column(name="customer_id",insertable = false, updatable = false)
	public Long customerId;
	
	@OneToOne
	public Customer customer;
	
	@Column(name="payed")
	Boolean payed;
	
	@Column(name="amount")
	Double amount;

	public String toCsvString() {

		return String.format("\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\"", 
				this.id,
				this.customer.id,
				this.customer.firstName, 
				this.customer.lastName,
				this.customer.height,
				this.customer.weight,
				this.customer.getAge(),
				this.amount,				
				this.payed);
	}
	
}
