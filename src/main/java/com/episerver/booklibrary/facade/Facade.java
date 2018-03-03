/**
 * Wraps all the services with facade pattern
 */
package com.episerver.booklibrary.facade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.episerver.booklibrary.exception.ApplicationException;
import com.episerver.booklibrary.model.BaseModel;
import com.episerver.booklibrary.model.Book;
import com.episerver.booklibrary.model.Magazine;
import com.episerver.booklibrary.service.AuthorService;
import com.episerver.booklibrary.service.BookService;
import com.episerver.booklibrary.service.MagazineService;
import com.episerver.booklibrary.utils.Constant;

/**
 * @author Tuan Nguyen
 *
 */
public class Facade {

	private AuthorService authorService;

	private BookService bookService;

	private MagazineService magazineService;

	private static Facade instance;

	/**
	 * Apply singleton pattern
	 */
	private Facade(String authorDsPath, String bookDsPath, String magazineDsPath) {
		authorService = AuthorService.getInstance(authorDsPath);
		bookService = BookService.getInstance(bookDsPath, authorDsPath);
		magazineService = MagazineService.getInstance(magazineDsPath, authorDsPath);
	}

	public Map<String, List<? extends BaseModel>> readAllData() throws ApplicationException {
		try {
			Map<String, List<? extends BaseModel>> all = new HashMap<>();

			all.put(Constant.AUTHORS_KEY, authorService.getAllAuthors());
			all.put(Constant.BOOKS_KEY, bookService.getAllBooks());
			all.put(Constant.MAGAZINES_KEY, magazineService.getAllMagazines());

			return all;
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage(), e);
		}
	}

	public Map<String, List<? extends BaseModel>> readAllBooksAndMagazines(boolean sort) throws ApplicationException {
		try {
			Map<String, List<? extends BaseModel>> all = new HashMap<>();

			List<Book> books = bookService.getAllBooks();
			List<Magazine> magazines = magazineService.getAllMagazines();

			if (sort) {
				books.sort((b1, b2) -> b1.getTitle().compareToIgnoreCase(b2.getTitle()));
				magazines.sort((m1, m2) -> m1.getTitle().compareToIgnoreCase(m2.getTitle()));
			}

			all.put(Constant.BOOKS_KEY, books);
			all.put(Constant.MAGAZINES_KEY, magazines);

			return all;
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage(), e);
		}
	}

	public Book findBookByIsbn(String isbn) throws ApplicationException {
		try {
			return bookService.getAllBooks().stream().filter(b -> b.getIsbn().equals(isbn)).findFirst().orElse(null);
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage(), e);
		}
	}

	public Magazine findMagazineByIsbn(String isbn) throws ApplicationException {
		try {
			return magazineService.getAllMagazines().stream().filter(m -> m.getIsbn().equals(isbn)).findFirst()
					.orElse(null);
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage(), e);
		}
	}

	public Book findBookByAuthor(String author) throws ApplicationException {
		try {
			return bookService.getAllBooks().stream().filter(b -> {
				return b.getAuthors().stream().anyMatch(a -> a.getEmail().equals(author));
			}).findFirst().orElse(null);
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage(), e);
		}
	}

	public Magazine findMagazineByAuthor(String author) throws ApplicationException {
		try {
			return magazineService.getAllMagazines().stream().filter(m -> {
				return m.getAuthors().stream().anyMatch(a -> a.getEmail().equals(author));
			}).findFirst().orElse(null);
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage(), e);
		}
	}

	public static Facade getInstance(String authorDsPath, String bookDsPath, String magazineDsPath) {
		if (instance == null) {
			instance = new Facade(authorDsPath, bookDsPath, magazineDsPath);
		}

		return instance;
	}
}
