package ca.xpertproject.apps.businessmanager.model;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface EventAttendeeRepository extends Repository<EventAttendee, Long> {

	EventAttendee save(EventAttendee eventAttendee);
			
	EventAttendee findById(Long id);
	
	List<EventAttendee> findAll();
	
	List<EventAttendee> findByEventId(Long eventId); 
	
	void deleteById(Long eventId);
}
