package ca.xpertproject.apps.businessmanager.dashboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import ca.xpertproject.apps.businessmanager.model.Payment;
import ca.xpertproject.apps.businessmanager.model.PaymentRepository;
import ca.xpertproject.apps.businessmanager.model.Subscription;
import ca.xpertproject.apps.businessmanager.model.SubscriptionRepository;
import ca.xpertproject.apps.businessmanager.objects.MonthlyCA;
import ca.xpertproject.apps.businessmanager.objects.MonthlySubNbr;
import ca.xpertproject.apps.businessmanager.objects.SubscriptionExt;
import ca.xpertproject.apps.businessmanager.objects.mappers.SubscriptionMapper;

@SpringBootTest
public class WidgetDataFeederTest {
	
	@MockitoBean
	private PaymentRepository paymentRepository;
	
	@MockitoBean
	private SubscriptionRepository subscriptionRepository;
	
	IWidgetsDataFeeder wdf = new WidgetsDataFeeder();
	
	EasyRandom easyRandom = new EasyRandom();

	@Test
	public void getCAByMonth_test() {
		
		List<Payment> payments = easyRandom.objects(Payment.class,5).collect(Collectors.toList());
		
		when(paymentRepository.findAll()).thenReturn(payments);
		
		List<MonthlyCA> monthlyCaList = wdf.getCAByMonth(paymentRepository, subscriptionRepository);
		
		List<MonthlyCA> expected = new ArrayList<MonthlyCA>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		
		TreeMap<String, Double> monthlyCAMap = new TreeMap<String, Double>();
		
		for (Payment payment : payments) {
			String paymentMonthStr = sdf.format(payment.getPaymentDate());

			Double monthlyCAAmount = monthlyCAMap.get(paymentMonthStr);

			if(monthlyCAAmount == null) {
				monthlyCAAmount = 0.0;
			}

			monthlyCAAmount += payment.amount;
			monthlyCAMap.put(paymentMonthStr, monthlyCAAmount);
		}

		Iterator<Entry<String, Double>> mapItr = monthlyCAMap.entrySet().iterator();

		while (mapItr.hasNext()) {
			Entry<String, Double> entry = (Entry<String, Double>) mapItr
					.next();
			MonthlyCA monthlyCA = new MonthlyCA(entry.getKey(), entry.getValue());

			expected.add(monthlyCA);
		}
		
		int listIdx = 0;
		for (MonthlyCA monthlyCA : expected) {
			assertEquals(expected.get(listIdx).getAmount().doubleValue(), monthlyCaList.get(listIdx).getAmount().doubleValue());
			
			assertEquals(expected.get(listIdx).getMonth(), monthlyCaList.get(listIdx).getMonth());
			listIdx++;
		}
		
	}
	
	@Test
	public void getSubsByMonth_test() {
		List<Subscription> subscriptionList = easyRandom.objects(Subscription.class,5).collect(Collectors.toList());
		
		when(subscriptionRepository.findAll()).thenReturn(subscriptionList);
		
		List<MonthlySubNbr> subsByMonthList = wdf.getSubsByMonth(subscriptionRepository);
		
		List<MonthlySubNbr> expected = new ArrayList<>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
				
		TreeMap<String, Integer> monthlySubMap = new TreeMap<String, Integer>();

		for (Subscription subscription : subscriptionList) {
			String monthStr = sdf.format(subscription.getSubscriptionDate());

			Integer monthlySubNbr = monthlySubMap.get(monthStr);

			if(monthlySubNbr == null) {
				monthlySubNbr = 0;
			}

			monthlySubNbr++;
			monthlySubMap.put(monthStr, monthlySubNbr);
		}

		Iterator<Entry<String, Integer>> mapItr = monthlySubMap.entrySet().iterator();

		while (mapItr.hasNext()) {
			Entry<String, Integer> entry = (Entry<String, Integer>) mapItr
					.next();
			MonthlySubNbr monthlySubNbr = new MonthlySubNbr(entry.getKey(), entry.getValue());

			expected.add(monthlySubNbr);
		}
		
		int listIdx = 0;
		for (MonthlySubNbr monthlySubNbr : expected) {
			assertEquals(monthlySubNbr.getSubNumber().intValue(), subsByMonthList.get(listIdx).getSubNumber().intValue());
			
			assertEquals(monthlySubNbr.getMonth(), subsByMonthList.get(listIdx).getMonth());
			listIdx++;
		}
	}
	
	@Test
	public void getValidSubscriptionNumber() {
		
		List<Subscription> subscriptionList = easyRandom.objects(Subscription.class,5).collect(Collectors.toList());
		
		when(subscriptionRepository.findAll()).thenReturn(subscriptionList);
		
		Integer subscriptionsNumber = wdf.getValidSubscriptionNumber(subscriptionRepository);
		
		List<SubscriptionExt> validSubscriptionList = subscriptionList.stream().map(sub-> new SubscriptionMapper().convert(sub)).collect(Collectors.toList());
		
		int expected = validSubscriptionList.stream().filter(t -> t.getIsValid()).collect(Collectors.toList()).size();

		assertEquals(expected, subscriptionsNumber.intValue());
		
	}
	
	@Test
	public void getPaymentsData_test() {
		
		List<Subscription> subscriptionList = easyRandom.objects(Subscription.class,5).collect(Collectors.toList());
		
		double totalSubscriptionAmount = 0.0;
		double totalPaymentAmount = 0.0;
		double totalAmountDue = 0.0;
		
		for (Subscription subscription : subscriptionList) {
			totalSubscriptionAmount += subscription.amount.doubleValue();
		}
				
		when(subscriptionRepository.findAll()).thenReturn(subscriptionList);
		
		List<Payment> payments = easyRandom.objects(Payment.class,5).collect(Collectors.toList());
		for (Payment payment : payments) {
			totalPaymentAmount += payment.getAmount().doubleValue();
		}
		
		when(paymentRepository.findAll()).thenReturn(payments);
		
		totalAmountDue = totalSubscriptionAmount - totalPaymentAmount;
		
		List<Double> paymentsData = wdf.getPaymentsData(paymentRepository, subscriptionRepository);
		
		assertEquals(totalPaymentAmount, paymentsData.get(0));
		assertEquals(totalAmountDue, paymentsData.get(1));
		
	}
	
	@Test
	public void getPartialOrUnpaidSubscriptions_test() {
		
		List<Subscription> subscriptions = easyRandom.objects(Subscription.class,5).collect(Collectors.toList());
		
		Payment fullpayment = easyRandom.nextObject(Payment.class);
		fullpayment.setAmount(1000.0);
		
		Payment partialpayment = easyRandom.nextObject(Payment.class);
		partialpayment.setAmount(500.0);
		
		subscriptions.get(0).setAmount(1000.0);
		subscriptions.get(0).setPaymentList(Collections.singletonList(fullpayment));
		
		subscriptions.get(1).setAmount(1000.0);
		subscriptions.get(1).setPaymentList(Collections.singletonList(partialpayment));
		
		Integer expected = 0;
		for (Subscription subscription : subscriptions) {
			Double paidAmount = 0.0;
			List<Payment> payments = subscription.getPaymentList();
			for(Payment payment : payments) {
				paidAmount += payment.amount;
			}

			if(paidAmount.doubleValue()<subscription.amount.doubleValue()) {
				expected ++;
			}
		}
		
		when(subscriptionRepository.findAll()).thenReturn(subscriptions);
		
		Integer unpaidSubNumber = wdf.getPartialOrUnpaidSubscriptions(subscriptionRepository);
		
		assertEquals(expected.intValue(), unpaidSubNumber.intValue());
	}
}
