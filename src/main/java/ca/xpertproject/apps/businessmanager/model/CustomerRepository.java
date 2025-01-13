package ca.xpertproject.apps.businessmanager.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;


public interface CustomerRepository extends Repository<Customer, Long> {
	
	Customer save(Customer customer);

	Customer findById(Long id);
	
	List<Customer> findAll();
	
	List<Customer> findByEmail(String email);

	
	@Query(
            value = "select * from customer c where c.lastname ILIKE %:name%",
            nativeQuery = true
    )
    List<Customer> findByName(String name);
	
	@Query(
            value = "select * from customer c where c.firstname ILIKE %:firstname%",
            nativeQuery = true
    )
    List<Customer> findByFirstName(String firstname);

}
