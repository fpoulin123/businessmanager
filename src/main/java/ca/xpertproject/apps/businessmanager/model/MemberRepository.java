package ca.xpertproject.apps.businessmanager.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;



public interface MemberRepository extends Repository<Member, Long>  {
	
	Member save(Member member);

	Optional<Member> findById(Long id);
	
	List<Member> findByEmail(String email);

}
