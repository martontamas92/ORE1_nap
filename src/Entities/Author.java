package Entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import lombok.Data;

@Data
@Entity
@Table(name = "author")

public class Author {
	EntityManagerFactory emf;
	EntityManager em;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "name", length = 255)
	private String name;
	//@Column(name = "birthDate")
	private Date birthDate;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private List<Book> book;
	public Author() {}
	public Author(String persistenceUnitName) {
		emf = Persistence.createEntityManagerFactory(persistenceUnitName);
		em = emf.createEntityManager();
	}

	Author addAuthor(String name, Date birthDate) {
		Author a = new Author();
		a.setBirthDate(birthDate);
		a.setName(name);
		em.getTransaction().begin();
		em.persist(a);
		em.getTransaction().commit();
		return a;

	}

	void removeAuthor(Author author) {
		Query  q = em.createQuery("DELETE FROM Author a where a.id = :id").setParameter("id", author.getId());
		q.executeUpdate();
	}

	void assignAuthorToBook(Author author, Book book) {

		book.getAuthor().add(author);
		em.getTransaction().begin();
		/*Query q = em.createQuery
				("UPDATE Book AS b SET b.author = :author WHERE b.id = :Bid")
				.setParameter("author", author)
				.setParameter("Bid", book.getId());
		q.executeUpdate();
		*/
		em.persist(book);
		em.getTransaction().commit();
	}



}
