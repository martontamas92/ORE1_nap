package main;

import java.util.List;

import Entities.Book;
import Entities.Book.Status;
import Entities.BookStore;

public class Main {
	public static void main(String[] args) {
		Book b = new Book();
		b.setAuthor("asd");
		b.setPages(304);
		b.setTitle("bas");
		b.setStockStatus(Status.IN_STOCK);
		//System.out.println(b.getId().toString());
		BookStore bs = new BookStore("testpu");
		bs.addBook(b);
		List<Book> bookok = bs.getAllBooks();
		for(int i = 0; i<bookok.size();i++) {
			System.out.println(bookok.get(i).toString());
		}

	}
}
