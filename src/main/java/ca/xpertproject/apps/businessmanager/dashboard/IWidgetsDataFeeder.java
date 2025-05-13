package ca.xpertproject.apps.businessmanager.dashboard;

import java.text.ParseException;
import java.util.List;

import ca.xpertproject.apps.businessmanager.model.EventAttendeeRepository;
import ca.xpertproject.apps.businessmanager.model.PaymentRepository;
import ca.xpertproject.apps.businessmanager.model.SubscriptionRepository;
import ca.xpertproject.apps.businessmanager.objects.MonthlyCA;
import ca.xpertproject.apps.businessmanager.objects.MonthlySubNbr;

public interface IWidgetsDataFeeder {

	
	public List<Double> getPaymentsData(PaymentRepository paymentRepository, SubscriptionRepository subscriptionRepository);
	
	public Integer getValidSubscriptionNumber(SubscriptionRepository subscriptionRepository);
	
	public Integer getPartialOrUnpaidSubscriptions(SubscriptionRepository subscriptionRepository) ;
	
	public List<MonthlyCA> getCAByMonth(PaymentRepository paymentRepository, SubscriptionRepository subscriptionRepository, EventAttendeeRepository eventAttendeeRepository, String year) throws ParseException;
	
	public List<MonthlySubNbr> getSubsByMonth(SubscriptionRepository subscriptionRepository);
}
