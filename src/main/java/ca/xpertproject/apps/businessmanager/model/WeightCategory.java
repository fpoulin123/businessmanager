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

@NoArgsConstructor
@AllArgsConstructor

@Builder

@Getter
@Setter

@Entity
@Table(name="weight_categpory")
public class WeightCategory {
	
	@Id
	@GeneratedValue(generator = "weight_category_id_seq")
	@SequenceGenerator(name="weight_category_id_seq", sequenceName = "weight_category_id_seq", allocationSize = 1)
	@Column(name="id")
	Long id;
	
	@Column(name="name")
	String name;
	
	@Column(name="gender")
	String gender;
	
	@Column(name="sport")
	String sport;
	
	@Column(name="federation")
	String federation;
	
	@Column(name="above_limit")
	Double aboveLimit;
	
	@Column(name="below_limit")
	Double belowLimit;

}
