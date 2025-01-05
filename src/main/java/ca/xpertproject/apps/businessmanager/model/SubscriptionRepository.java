package ca.xpertproject.apps.businessmanager.model;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface SubscriptionRepository extends Repository<Subscription, Long> {
	
	Subscription save(Subscription payment);

	Subscription findById(Long id);
	
	List<Subscription> findAll();
	
	List<Subscription> findByCustomerId(Long customerId);

}
