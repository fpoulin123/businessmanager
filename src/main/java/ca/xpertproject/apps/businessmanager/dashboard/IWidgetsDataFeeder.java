package ca.xpertproject.apps.businessmanager.dashboard;

import java.util.List;

import ca.xpertproject.apps.businessmanager.model.PaymentRepository;
import ca.xpertproject.apps.businessmanager.model.SubscriptionRepository;
import ca.xpertproject.apps.businessmanager.objects.MonthlyCA;
import ca.xpertproject.apps.businessmanager.objects.MonthlySubNbr;

public interface IWidgetsDataFeeder {

	
	public List<Double> getPaymentsData(PaymentRepository paymentRepository, SubscriptionRepository subscriptionRepository);
	
	public Integer getValidSubscriptionNumber(SubscriptionRepository subscriptionRepository);
	
	public Integer getPartialOrUnpaidSubscriptions(SubscriptionRepository subscriptionRepository) ;
	
	public List<MonthlyCA> getCAByMonth(PaymentRepository paymentRepository, SubscriptionRepository subscriptionRepository);
	
	public List<MonthlySubNbr> getSubsByMonth(SubscriptionRepository subscriptionRepository);
}
