package ca.xpertproject.apps.businessmanager.objects.mappers;

import java.text.SimpleDateFormat;

import ca.xpertproject.apps.businessmanager.model.Customer;
import ca.xpertproject.apps.businessmanager.model.Event;
import ca.xpertproject.apps.businessmanager.objects.CalendarEvent;
import ca.xpertproject.apps.businessmanager.objects.CustomerLight;
import ca.xpertproject.apps.businessmanager.objects.EventForAC;

public class EventMapper {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public CalendarEvent convert(Event event) {
		
		
		CalendarEvent calendarEvent = new CalendarEvent();
		calendarEvent.setTitle(event.getEventName());
		calendarEvent.setStart(sdf.format(event.getEventDate()));
		
		if(event.getEndDate()!=null&&event.getEndDate().after(event.getEventDate())) {
			calendarEvent.setEnd(sdf.format(event.endDate) + " 23:59:59");
		}else {
			calendarEvent.setEnd(calendarEvent.getStart());
		}
		
		calendarEvent.setUrl("./event?id=" +event.id);
		
		return calendarEvent;
		
	}
	
	public static EventForAC convertForList(Event event) {

		return new EventForAC(event.getEventName(), event.getId().toString());
	}

}
