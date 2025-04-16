package ca.xpertproject.apps.businessmanager.objects;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class CalendarData {
	
	String currentDate;
	
	List<CalendarEvent> eventList;

}
