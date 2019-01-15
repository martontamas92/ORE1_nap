package Entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of= {"id"})
@Entity

@Table(name = "books")
@NamedQueries({
	@NamedQuery(
				name = "getAllProducts",
				query = "select b from Book b order by b.id desc"
			)
})

public class Book {

	public enum Status {IN_STOCK, OUT_OF_STOCK, UNKNOWN};
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Long id;

	@Column(name = "title", length = 255, nullable = false)

	private String title;

	@Column(name = "author", length = 255, nullable = false)

	private String author;
	@Temporal(TemporalType.TIME)
	private Date publishedAt;

	@Column(nullable = true)
	private int pages;
	@Column(nullable = true)
	private BigDecimal price;
	@Enumerated(EnumType.STRING)
	private Status StockStatus;


}
