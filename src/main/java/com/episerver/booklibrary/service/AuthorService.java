/**
 * 
 */
package com.episerver.booklibrary.service;

import java.util.List;

import com.episerver.booklibrary.exception.ServiceException;
import com.episerver.booklibrary.model.Author;
import com.episerver.booklibrary.repository.AuthorRepository;

/**
 * @author Tuan Nguyen
 *
 */
public class AuthorService {

	private AuthorRepository repository;

	private static AuthorService instance = null;

	/**
	 * Apply singleton pattern
	 */
	private AuthorService(String dsPath) {
		repository = AuthorRepository.getInstance(dsPath);
	}

	public List<Author> getAllAuthors() throws ServiceException {
		try {
			return this.repository.getAllAuthors();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public static AuthorService getInstance(String dsPath) {
		if (instance == null) {
			instance = new AuthorService(dsPath);
		}

		return instance;
	}

}
