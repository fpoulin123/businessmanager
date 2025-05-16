package ca.xpertproject.apps.businessmanager.dashboard;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import ca.xpertproject.apps.businessmanager.model.CustomerRepository;
import ca.xpertproject.apps.businessmanager.model.EventAttendee;
import ca.xpertproject.apps.businessmanager.model.EventAttendeeRepository;
import ca.xpertproject.apps.businessmanager.model.Payment;
import ca.xpertproject.apps.businessmanager.model.PaymentRepository;
import ca.xpertproject.apps.businessmanager.model.Purchase;
import ca.xpertproject.apps.businessmanager.model.PurchaseRepository;
import ca.xpertproject.apps.businessmanager.model.Subscription;
import ca.xpertproject.apps.businessmanager.model.SubscriptionRepository;
import ca.xpertproject.apps.businessmanager.objects.MonthlyCA;
import ca.xpertproject.apps.businessmanager.objects.MonthlySubNbr;
import ca.xpertproject.apps.businessmanager.objects.PurchaseExt;
import ca.xpertproject.apps.businessmanager.objects.SubscriptionExt;
import ca.xpertproject.apps.businessmanager.objects.mappers.PurchaseMapper;
import ca.xpertproject.apps.businessmanager.objects.mappers.SubscriptionMapper;

public class WidgetsDataFeeder implements IWidgetsDataFeeder{

	public List<Double> getPaymentsData(PaymentRepository paymentRepository, SubscriptionRepository subscriptionRepository){
		List<Double> paymentsData = new ArrayList<>();

		Double totalPayments = 0.0;
		Double totalSubscriptions = 0.0;
		List<Payment> payments = paymentRepository.findAll();
		List<Subscription> subscriptions = subscriptionRepository.findAll();

		for (Subscription subscription : subscriptions) {
			totalSubscriptions += subscription.amount;
		}

		for (Payment payment : payments) {
			totalPayments += payment.amount;
		}

		Double totalDue = totalSubscriptions - totalPayments;

		paymentsData.add(totalPayments);
		paymentsData.add(totalDue);

		return paymentsData;
	}

	public Integer getValidSubscriptionNumber(SubscriptionRepository subscriptionRepository) {
		List<Subscription> subscriptions = subscriptionRepository.findAll();

		List<SubscriptionExt> subscriptionExtList = subscriptions.stream().map(sub-> new SubscriptionMapper().convert(sub)).filter(t -> t.getIsValid()).collect(Collectors.toList());


		return subscriptionExtList.size();
	}

	public Integer getPartialOrUnpaidSubscriptions(SubscriptionRepository subscriptionRepository) {
		List<Subscription> subscriptions = subscriptionRepository.findAll();
		Integer unpaidSubs = 0;
		for (Subscription subscription : subscriptions) {
			Double paidAmount = 0.0;
			List<Payment> payments = subscription.getPaymentList();
			for(Payment payment : payments) {
				paidAmount += payment.amount;
			}

			if(paidAmount.doubleValue()<subscription.amount.doubleValue()) {
				unpaidSubs ++;
			}
		}

		return unpaidSubs;
	}

	private boolean isInYear(Date inputDate, Integer year){
		boolean isInYear = false;
		
		SimpleDateFormat filterSdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Integer yearBefore = year - 1;
		
		Integer yearAfter = year + 1;
		
		Date afterDate = null;
		try {
			afterDate = filterSdf.parse(yearBefore  + "-12-31");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Date beforeDate = null;
		try {
			beforeDate = filterSdf.parse(yearAfter + "-01-01");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return inputDate.after(afterDate)&&inputDate.before(beforeDate);
	}
	
	
	public List<MonthlyCA> getCAByMonth(PaymentRepository paymentRepository, SubscriptionRepository subscriptionRepository, EventAttendeeRepository eventAttendeeRepository, PurchaseRepository purchaseRepository, String year) throws ParseException{

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

		List<MonthlyCA> monthlyCAList = new ArrayList<>();

		List<Payment> paymentList = paymentRepository.findAll();
		
		List<EventAttendee> attendeeList = eventAttendeeRepository.findAll();
		
		List<Purchase> purchaseList = purchaseRepository.findAll();
		
		
				
		if(year!=null) {
			Integer yearInt = Integer.parseInt(year);
				
			
			paymentList = paymentList.stream().filter(p->isInYear(p.getPaymentDate(), yearInt)).collect(Collectors.toList());
			
			attendeeList = attendeeList.stream().filter(a->isInYear(a.getEvent().getEventDate(),yearInt)).collect(Collectors.toList());
		
			purchaseList = purchaseList.stream().filter(s->isInYear(s.getPurchaseDate(), yearInt)).collect(Collectors.toList());
		
		}
		
		List<PurchaseExt> purchaseExtList = purchaseList.stream().map(PurchaseMapper::convert).collect(Collectors.toList());

		TreeMap<String, MonthlyCA> monthlyCAMap = new TreeMap<String, MonthlyCA>();
		
		
		for (Payment payment : paymentList) {
			if(payment.amount>0.0) {
				String paymentMonthStr = sdf.format(payment.getPaymentDate());
	
				MonthlyCA monthlyCA = monthlyCAMap.get(paymentMonthStr);
	
				if(monthlyCA == null) {
					monthlyCA = new MonthlyCA();
					monthlyCA.setMonth(paymentMonthStr);
					monthlyCA.setSubsamount(0.0);
					monthlyCA.setEventsamount(0.0);
					monthlyCA.setMerchsamount(0.0);
				}
	
				Double subsAmount = monthlyCA.getSubsamount();
				subsAmount += payment.getAmount();
				
				monthlyCA.setSubsamount(subsAmount);
				monthlyCAMap.put(paymentMonthStr, monthlyCA);
			}
		}
		
		for (EventAttendee attendee : attendeeList) {
			if(attendee.getAmount()>0.0&&attendee.getPayed()) {
				String eventMonthStr = sdf.format(attendee.getEvent().getEventDate());
	
				MonthlyCA monthlyCA = monthlyCAMap.get(eventMonthStr);
	
				if(monthlyCA == null) {
					monthlyCA = new MonthlyCA();
					monthlyCA.setMonth(eventMonthStr);
					monthlyCA.setSubsamount(0.0);
					monthlyCA.setEventsamount(0.0);
					monthlyCA.setMerchsamount(0.0);
				}
	
				Double eventsAmount = monthlyCA.getEventsamount();
				eventsAmount += attendee.getAmount();
				
				monthlyCA.setEventsamount(eventsAmount);
				monthlyCAMap.put(eventMonthStr, monthlyCA);
			}
		}
		
		for (PurchaseExt purchase : purchaseExtList) {
			if(purchase.getPurchaseAmount()>0.0) {
				String purchaseMonthStr = sdf.format(purchase.getPurchaseDate());
	
				MonthlyCA monthlyCA = monthlyCAMap.get(purchaseMonthStr);
	
				if(monthlyCA == null) {
					monthlyCA = new MonthlyCA();
					monthlyCA.setMonth(purchaseMonthStr);
					monthlyCA.setSubsamount(0.0);
					monthlyCA.setEventsamount(0.0);
					monthlyCA.setMerchsamount(0.0);
				}
	
				Double purchasesAmount = monthlyCA.getMerchsamount();
				purchasesAmount += purchase.getPurchaseAmount();
				
				monthlyCA.setMerchsamount(purchasesAmount);
				monthlyCAMap.put(purchaseMonthStr, monthlyCA);
			}
		}

		
		Iterator<Entry<String, MonthlyCA>> monthlyCAItr = monthlyCAMap.entrySet().iterator();

		while (monthlyCAItr.hasNext()) {
			Entry<String, MonthlyCA> entry = (Entry<String, MonthlyCA>) monthlyCAItr.next();
			
			monthlyCAList.add(entry.getValue());
			
		}
			
		return monthlyCAList;
	}

	public List<MonthlySubNbr> getSubsByMonth(SubscriptionRepository subscriptionRepository){

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

		List<MonthlySubNbr> monthlySubNbrList = new ArrayList<>();

		List<Subscription> subscriptionList = subscriptionRepository.findAll();

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

			monthlySubNbrList.add(monthlySubNbr);
		}

		return monthlySubNbrList;
	}

}
