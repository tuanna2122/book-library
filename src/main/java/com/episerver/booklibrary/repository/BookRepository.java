/**
 * 
 */
package com.episerver.booklibrary.repository;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.episerver.booklibrary.exception.DataAccessException;
import com.episerver.booklibrary.model.Author;
import com.episerver.booklibrary.model.Book;
import com.episerver.booklibrary.utils.CommonUtils;
import com.episerver.booklibrary.utils.Constant;

/**
 * @author Tuan Nguyen
 *
 */
public class BookRepository {

	private String dsPath;

	private String authorDsPath;

	/**
	 * Apply singleton pattern
	 */
	private BookRepository(String dsPath, String authorDsPath) {
		this.dsPath = dsPath;
		this.authorDsPath = authorDsPath;
	}

	private static BookRepository instance = null;

	/**
	 * Map from a csv line to Book object
	 */
	private Function<String, Book> mapToBook = (line) -> {
		String[] data = line.split(Constant.SEMI_COLON);
		Book book = new Book();

		book.setTitle(CommonUtils.trim(data[0]));
		book.setIsbn(CommonUtils.trim(data[1]));

		Set<Author> authors = Arrays.stream(CommonUtils.trim(data[2]).split(Constant.COMMA)).map(e -> {
			Author author = AuthorRepository.getInstance(this.authorDsPath).findByEmail(e);
			return author;
		}).collect(Collectors.toSet());
		book.setAuthors(authors);

		book.setDescription(CommonUtils.trim(data[3]));

		return book;
	};

	public List<Book> getAllBooks() throws DataAccessException {
		List<Book> books = new ArrayList<>();
		Class<BookRepository> clazz = BookRepository.class;

		try (InputStream is = clazz.getResourceAsStream(this.dsPath);
				BufferedReader br = new BufferedReader(new InputStreamReader(is, Constant.CHARSET_UTF_8));) {

			// The header line must be skipped
			books = br.lines().skip(1).map(mapToBook).collect(Collectors.toList());

		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(), e);
		}

		return books;
	}

	public static BookRepository getInstance(String dsPath, String authorDsPath) {
		if (instance == null) {
			instance = new BookRepository(dsPath, authorDsPath);
		}

		return instance;
	}

}
