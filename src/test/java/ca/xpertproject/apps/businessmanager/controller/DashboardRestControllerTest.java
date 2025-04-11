package ca.xpertproject.apps.businessmanager.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;

import ca.xpertproject.apps.businessmanager.model.CustomerRepository;
import ca.xpertproject.apps.businessmanager.model.PaymentRepository;
import ca.xpertproject.apps.businessmanager.model.SubscriptionRepository;
import ca.xpertproject.apps.businessmanager.objects.MonthlyCA;
import ca.xpertproject.apps.businessmanager.objects.MonthlySubNbr;

@WebMvcTest(DashboardRestController.class)
public class DashboardRestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private PaymentRepository paymentRepository;

	@MockitoBean
	private SubscriptionRepository subscriptionRepository;

	@MockitoBean
	private CustomerRepository customerRepository;
	
	
	@Test
	public void getPaymentsData() throws Exception {
		mockMvc.perform(get("/paymentsData")).andExpect(status().isOk());
	}
	
	@Test
	public void getValidSubscriptionNumber() throws Exception {
		mockMvc.perform(get("/validSubscriptionNumber")).andExpect(status().isOk());
	
	}
	
	@Test
	public void getUnpaidSubs() throws Exception  {
		mockMvc.perform(get("/unpaidSubs")).andExpect(status().isOk());
	}
	
	@Test
	public void getMonthlyCA() throws Exception  {
		mockMvc.perform(get("/monthlyCA")).andExpect(status().isOk());
	}
	
	@Test
	public void getMonthlySubs() {
		
	}
}
