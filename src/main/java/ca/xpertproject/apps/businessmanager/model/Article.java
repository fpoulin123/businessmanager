package ca.xpertproject.apps.businessmanager.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="article")

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

@Builder

public class Article {

	@Id
	@GeneratedValue(generator = "article_id_seq")
	@SequenceGenerator(name="article_id_seq", sequenceName = "article_id_seq", allocationSize = 1)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;

	@Column(name="price")
	private Double price;
	
	@OneToMany(mappedBy="articleId")
	public List<Purchase> purchaseList;

}
