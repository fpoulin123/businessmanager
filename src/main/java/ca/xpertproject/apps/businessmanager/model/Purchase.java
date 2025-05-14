package ca.xpertproject.apps.businessmanager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="purchase")

@NoArgsConstructor
@AllArgsConstructor

@Setter
@Getter

@Builder

public class Purchase {
	
	@Id
	@GeneratedValue(generator = "purchase_id_seq")
	@SequenceGenerator(name="purchase_id_seq", sequenceName = "purchase_id_seq", allocationSize = 1)
	@Column(name="id")
	private Long id;
	
	@Column(name="customer_id",insertable = false, updatable = false)
	public Long customerId;
	
	@ManyToOne
	public Customer customer;
	
	@Column(name="article_id")
	private Long articleId;

	@Column(name="number")
	private Integer number;
	
	@Column(name="discount")	
	private Double discount;

}
