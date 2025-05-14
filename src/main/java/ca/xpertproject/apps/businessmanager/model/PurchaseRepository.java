package ca.xpertproject.apps.businessmanager.model;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface PurchaseRepository extends Repository<Purchase, Long> {
	

	Purchase save(Purchase purchase);

	Purchase findById(Long id);
	
	List<Purchase> findAll();

}
