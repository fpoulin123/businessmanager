package ca.xpertproject.apps.businessmanager.model;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface EventRepository extends Repository<Event, Long> {
	
	Event save(Event event);

	Event findById(Long id);
	
	List<Event> findAll();
	
	List<Event> findByEventName(String eventName);
	
	List<Event> findByEventType(String eventType);

}
