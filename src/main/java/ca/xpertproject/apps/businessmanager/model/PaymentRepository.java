package ca.xpertproject.apps.businessmanager.model;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface PaymentRepository extends Repository<Payment, Long> {
	
	Payment save(Payment payment);
	
	Payment findById(Long id);
	
	List<Payment> findAll();
	
	List<Payment> findBySubscriptionId(Long subscriptionId);

}
