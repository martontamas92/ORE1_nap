package Entities;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "bookstore")
public class BookStore {


	@Id
	private int id;
	private List<Book> books = new ArrayList<Book>();
	EntityManagerFactory emf;
	EntityManager em;
	public BookStore() {}
	public BookStore(String persistenceUnitName) {
		emf = Persistence.createEntityManagerFactory(persistenceUnitName);
		em = emf.createEntityManager();

	}

	public Long addBook(Book newBook) {
		//books.add(newBook);
		em.getTransaction().begin();
		em.persist(newBook);
		em.getTransaction().commit();
		return newBook.getId();
	}
	public void removeBookById(int bookId) {
		//books.remove(bookId);
//		em.getTransaction().begin();
		Query q = em.createQuery("DELETE FROM booksstore b where  b.id = :bookId").setParameter("bookId", bookId);
		q.executeUpdate();
	}

	public List<Book> getAllBooks() {
		Query q = em.createQuery("SELECT b FROM books b",Book.class);
		return q.getResultList();
	}








}
