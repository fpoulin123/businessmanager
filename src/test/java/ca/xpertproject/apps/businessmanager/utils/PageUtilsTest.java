package ca.xpertproject.apps.businessmanager.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

@SpringBootTest
public class PageUtilsTest {
	
	@Mock
	Model model;
	
	@Test
	public void getPagedItems_happyPath() {
		
		List<String> testList = new ArrayList<String>();
		
		for(int i=0;i<50;i++) {
			testList.add(UUID.randomUUID().toString());
			
		}
		
		PageUtils.getPagedItems(testList, model, "2", "testList");
		
		verify(model, times(1)).addAttribute("currentPage", 2);
		
		verify(model, times(1)).addAttribute("pages", 5);
		
		
	}
	
	@Test
	public void getPagedItems_noItems() {
		
		List<String> testList = new ArrayList<String>();
		
		assertEquals(testList, PageUtils.getPagedItems(testList, model, null, "testList"));
		
		
		
	}
	

}
