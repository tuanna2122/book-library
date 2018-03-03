/**
 * 
 */
package com.episerver.booklibrary.repository;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.episerver.booklibrary.exception.DataAccessException;
import com.episerver.booklibrary.model.Author;
import com.episerver.booklibrary.utils.CommonUtils;
import com.episerver.booklibrary.utils.Constant;

/**
 * @author Tuan Nguyen
 *
 */
public class AuthorRepository {

	private String dsPath;

	/**
	 * Apply singleton pattern
	 */
	private AuthorRepository(String dsPath) {
		this.dsPath = dsPath;
	}

	private static AuthorRepository instance = null;

	/**
	 * Map from a csv line to Author object
	 */
	private Function<String, Author> mapToAuthor = (line) -> {
		String[] data = line.split(Constant.SEMI_COLON);
		Author author = new Author(CommonUtils.trim(data[0]), CommonUtils.trim(data[1]), CommonUtils.trim(data[2]));
		return author;
	};

	public List<Author> getAllAuthors() throws DataAccessException {
		List<Author> authors = new ArrayList<>();
		Class<AuthorRepository> clazz = AuthorRepository.class;

		try (InputStream is = clazz.getResourceAsStream(this.dsPath);
				BufferedReader br = new BufferedReader(new InputStreamReader(is, Constant.CHARSET_UTF_8));) {

			// The header line must be skipped
			authors = br.lines().skip(1).map(mapToAuthor).collect(Collectors.toList());

		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(), e);
		}

		return authors;
	}

	public Author findByEmail(String email) {
		return getAllAuthors().stream().filter(a -> a.getEmail().equals(email)).findFirst().orElse(new Author());
	}

	public static AuthorRepository getInstance(String dsPath) {
		if (instance == null) {
			instance = new AuthorRepository(dsPath);
		}

		return instance;
	}

}
