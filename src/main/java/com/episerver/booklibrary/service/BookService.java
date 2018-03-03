/**
 * 
 */
package com.episerver.booklibrary.service;

import java.util.List;

import com.episerver.booklibrary.exception.ServiceException;
import com.episerver.booklibrary.model.Book;
import com.episerver.booklibrary.repository.BookRepository;

/**
 * @author Tuan Nguyen
 *
 */
public class BookService {

	private BookRepository repository;

	private static BookService instance = null;

	/**
	 * Apply singleton pattern
	 */
	private BookService(String dsPath, String authorDsPath) {
		repository = BookRepository.getInstance(dsPath, authorDsPath);
	}

	public List<Book> getAllBooks() throws ServiceException {
		try {
			return this.repository.getAllBooks();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public static BookService getInstance(String dsPath, String authorDsPath) {
		if (instance == null) {
			instance = new BookService(dsPath, authorDsPath);
		}

		return instance;
	}

}
