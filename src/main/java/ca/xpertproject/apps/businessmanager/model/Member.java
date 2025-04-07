package ca.xpertproject.apps.businessmanager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "member")

@NoArgsConstructor
@AllArgsConstructor


@Builder
@Getter
@Setter
public class Member {
	
	@Id
	@GeneratedValue(generator = "member_id_seq")
	@SequenceGenerator(name="member_id_seq", sequenceName = "member_id_seq", allocationSize = 1)
	Long id;
	
	@Column(name="firstname")
	String firstname;
	
	@Column(name="lastname")
	String lastname;
	
	@Column(name="email")
	String email;
	
	@Column(name="password")
	String password;
	
	@Column(name="active")
	Boolean active;

	
}
