package ca.xpertproject.apps.businessmanager.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;


public interface CustomerRepository extends Repository<Customer, Long> {
	
	Customer save(Customer customer);

	Customer findById(Long id);
	
	List<Customer> findAll();
	
	List<Customer> findByEmail(String email);

}
