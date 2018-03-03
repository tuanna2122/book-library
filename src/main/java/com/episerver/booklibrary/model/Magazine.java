/**
 * Model class for Magazine object
 */
package com.episerver.booklibrary.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Tuan Nguyen
 *
 */
public class Magazine extends BaseModel {

	private String title;

	private String isbn;

	private Set<Author> authors = new HashSet<>();

	private String publishDate;

	public Magazine() {
	}

	public Magazine(String title, String isbn, Set<Author> authors, String publishDate) {
		this.title = title;
		this.isbn = isbn;
		this.authors = authors;
		this.publishDate = publishDate;
	}

	public String getTitle() {
		return title;
	}

	public String getIsbn() {
		return isbn;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Magazine [title=");
		builder.append(title);
		builder.append(", isbn=");
		builder.append(isbn);
		builder.append(", authors=");
		builder.append(authors);
		builder.append(", publishDate=");
		builder.append(publishDate);
		builder.append("]");
		return builder.toString();
	}

}
