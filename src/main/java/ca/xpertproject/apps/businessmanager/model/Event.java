package ca.xpertproject.apps.businessmanager.model;

import java.util.Date;

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
	

	@Override
	public String toCsvString() {
		
		return null;
	}

}
