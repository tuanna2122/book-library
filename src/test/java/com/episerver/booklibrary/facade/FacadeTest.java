/**
 * Test class for {@link Facade}
 */
package com.episerver.booklibrary.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.episerver.booklibrary.model.BaseModel;
import com.episerver.booklibrary.model.Book;
import com.episerver.booklibrary.model.Magazine;
import com.episerver.booklibrary.utils.Constant;

/**
 * @author Tuan Nguyen
 *
 */
public class FacadeTest {

	private Facade facade;

	@Before
	public void setup() {
		this.facade = Facade.getInstance("/authors.csv", "/books.csv", "/magazines.csv");
	}

	@Test
	public void testReadAllData() {
		Map<String, List<? extends BaseModel>> all = facade.readAllData();
		assertEquals(3, all.size());
		assertEquals(6, all.get(Constant.AUTHORS_KEY).size());
		assertEquals(8, all.get(Constant.BOOKS_KEY).size());
		assertEquals(6, all.get(Constant.MAGAZINES_KEY).size());
	}

	@Test
	public void testReadAllBooksAndMagazinesWithoutSorting() {
		Map<String, List<? extends BaseModel>> all = facade.readAllBooksAndMagazines(false);
		assertEquals(2, all.size());
		assertNull(all.get(Constant.AUTHORS_KEY));
		assertEquals(8, all.get(Constant.BOOKS_KEY).size());
		assertEquals(6, all.get(Constant.MAGAZINES_KEY).size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testReadAllBooksAndMagazinesWithSorting() {
		Map<String, List<? extends BaseModel>> all = facade.readAllBooksAndMagazines(true);
		assertEquals(2, all.size());
		assertNull(all.get(Constant.AUTHORS_KEY));
		assertTrue(((List<Book>) all.get(Constant.BOOKS_KEY)).get(0).getTitle().startsWith("Das"));
		assertTrue(((List<Magazine>) all.get(Constant.MAGAZINES_KEY)).get(0).getTitle().startsWith("Der"));
	}

	@Test
	public void testFindBookByIsbnWillReturnNull() {
		Book book = facade.findBookByIsbn("test");
		assertNull(book);
	}

	@Test
	public void testFindBookByIsbnWillReturnValidValue() {
		Book book = facade.findBookByIsbn("2145-8548-3325");
		assertNotNull(book);
		assertTrue(book.getTitle().startsWith("Das"));
	}

	@Test
	public void testFindMagazineByIsbnWillReturnNull() {
		Magazine magazine = facade.findMagazineByIsbn("test");
		assertNull(magazine);
	}

	@Test
	public void testFindMagazineByIsbnWillReturnValidValue() {
		Magazine magazine = facade.findMagazineByIsbn("2365-5632-7854");
		assertNotNull(magazine);
		assertTrue(magazine.getTitle().startsWith("Kochen"));
	}

	@Test
	public void testFindBookByAuthorWillReturnNull() {
		Book book = facade.findBookByAuthor("test");
		assertNull(book);
	}

	@Test
	public void testFindBookByAuthorWillReturnValidValue() {
		Book book = facade.findBookByAuthor("pr-ferdinand@optivo.de");
		assertNotNull(book);
		assertTrue(book.getTitle().startsWith("Das"));
	}

	@Test
	public void testFindMagazineByAuthorWillReturnNull() {
		Magazine magazine = facade.findMagazineByAuthor("test");
		assertNull(magazine);
	}

	@Test
	public void testFindMagazineByAuthorWillReturnValidValue() {
		Magazine magazine = facade.findMagazineByAuthor("pr-mueller@optivo.de");
		assertNotNull(magazine);
		assertTrue(magazine.getPublishDate().equals("10.07.2006"));
	}

}
