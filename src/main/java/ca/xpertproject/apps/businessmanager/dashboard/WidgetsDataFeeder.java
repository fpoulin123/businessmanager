package ca.xpertproject.apps.businessmanager.dashboard;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import ca.xpertproject.apps.businessmanager.model.CustomerRepository;
import ca.xpertproject.apps.businessmanager.model.Payment;
import ca.xpertproject.apps.businessmanager.model.PaymentRepository;
import ca.xpertproject.apps.businessmanager.model.Subscription;
import ca.xpertproject.apps.businessmanager.model.SubscriptionRepository;
import ca.xpertproject.apps.businessmanager.objects.MonthlyCA;
import ca.xpertproject.apps.businessmanager.objects.MonthlySubNbr;
import ca.xpertproject.apps.businessmanager.objects.SubscriptionExt;
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

	public List<MonthlyCA> getCAByMonth(PaymentRepository paymentRepository, SubscriptionRepository subscriptionRepository, String year) throws ParseException{

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

		List<MonthlyCA> monthlyCAList = new ArrayList<>();

		List<Payment> paymentList = paymentRepository.findAll();
		
		if(year!=null) {
			SimpleDateFormat filterSdf = new SimpleDateFormat("yyyy-MM-dd");
			
			Date afterDate = filterSdf.parse(year + "-01-01");
			
			Date beforeDate = filterSdf.parse(year + "-12-31");
			
			paymentList = paymentList.stream().filter(p->p.getPaymentDate().after(afterDate)&&p.getPaymentDate().before(beforeDate)).collect(Collectors.toList());
		}

		TreeMap<String, Double> monthlyCAMap = new TreeMap<String, Double>();

		for (Payment payment : paymentList) {
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

			monthlyCAList.add(monthlyCA);
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
