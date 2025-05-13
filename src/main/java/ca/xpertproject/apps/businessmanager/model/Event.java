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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="event")


@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

@Builder
public class Event implements CSVTransformable{
	
	@Id
	@GeneratedValue(generator = "event_id_seq")
	@SequenceGenerator(name="event_id_seq", sequenceName = "event_id_seq", allocationSize = 1)
	@Column(name="id")
	public Long id;
	
	@Column(name="event_name")
	public String eventName;
	
	@Column(name="event_type")
	public String eventType;
	
	@Column(name="event_date")
	public Date eventDate;
	
	@Column(name="amount")
	public Double amount;
	
	@Column(name="end_date")
	public Date endDate;
	
	@OneToMany(mappedBy = "eventId")
	public List<EventAttendee> attendeeList;
	

	@Override
	public String toCsvString() {
		
		return String.format("\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";", 
				this.id,
				this.eventName,
				this.eventType,
				new SimpleDateFormat("yyyy-MM-dd").format(this.eventDate),
				endDate!=null?new SimpleDateFormat("yyyy-MM-dd").format(this.endDate):new SimpleDateFormat("yyyy-MM-dd").format(this.eventDate),
				this.amount);
	}

}
