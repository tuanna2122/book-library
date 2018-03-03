/**
 * Model class for Book object
 */
package com.episerver.booklibrary.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Tuan Nguyen
 *
 */
public class Book extends BaseModel {

	private String title;

	private String isbn;

	private String description;

	private Set<Author> authors = new HashSet<>();

	public Book() {
	}

	public Book(String title, String isbn, String description, Set<Author> authors) {
		this.title = title;
		this.isbn = isbn;
		this.description = description;
		this.authors = authors;
	}

	public String getTitle() {
		return title;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getDescription() {
		return description;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Book [title=");
		builder.append(title);
		builder.append(", isbn=");
		builder.append(isbn);
		builder.append(", description=");
		builder.append(description);
		builder.append(", authors=");
		builder.append(authors);
		builder.append("]");
		return builder.toString();
	}

}
