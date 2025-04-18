package ca.xpertproject.apps.businessmanager.objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class CalendarEvent {

	String title;
	
	String start;
	
	String end;
	
	String url;
}
