package ca.xpertproject.apps.businessmanager.objects.mappers;

import java.text.SimpleDateFormat;

import ca.xpertproject.apps.businessmanager.model.Event;
import ca.xpertproject.apps.businessmanager.objects.CalendarEvent;

public class EventMapper {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public CalendarEvent convert(Event event) {
		
		
		CalendarEvent calendarEvent = new CalendarEvent();
		calendarEvent.setTitle(event.getEventName());
		calendarEvent.setStart(sdf.format(event.getEventDate()));
		
		return calendarEvent;
		
	}

}
