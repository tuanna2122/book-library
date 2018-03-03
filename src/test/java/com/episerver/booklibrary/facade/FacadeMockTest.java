/**
 * 
 */
package com.episerver.booklibrary.facade;

import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.episerver.booklibrary.exception.ApplicationException;

/**
 * @author Tuan Nguyen
 *
 */
public class FacadeMockTest {

	@SuppressWarnings("unchecked")
	@Test(expected = ApplicationException.class)
	public void testReadAllWillThrowException() {
		Facade facade = mock(Facade.class);
		when(facade.readAllData()).thenThrow(ApplicationException.class);
		facade.readAllData();
	}

	@SuppressWarnings("unchecked")
	@Test(expected = ApplicationException.class)
	public void testReadAllBooksAndMagazinesWillThrowException() {
		Facade facade = mock(Facade.class);
		when(facade.readAllBooksAndMagazines(anyBoolean())).thenThrow(ApplicationException.class);
		facade.readAllBooksAndMagazines(true);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = ApplicationException.class)
	public void testFindBookByIsbnWillThrowException() {
		Facade facade = mock(Facade.class);
		when(facade.findBookByIsbn(anyString())).thenThrow(ApplicationException.class);
		facade.findBookByIsbn("test");
	}

	@SuppressWarnings("unchecked")
	@Test(expected = ApplicationException.class)
	public void testFindBookByAuthorWillThrowException() {
		Facade facade = mock(Facade.class);
		when(facade.findBookByAuthor(anyString())).thenThrow(ApplicationException.class);
		facade.findBookByAuthor("test");
	}

	@SuppressWarnings("unchecked")
	@Test(expected = ApplicationException.class)
	public void testFindMagazineByIsbnWillThrowException() {
		Facade facade = mock(Facade.class);
		when(facade.findMagazineByIsbn(anyString())).thenThrow(ApplicationException.class);
		facade.findMagazineByIsbn("test");
	}

	@SuppressWarnings("unchecked")
	@Test(expected = ApplicationException.class)
	public void testFindMagazineByAuthorWillThrowException() {
		Facade facade = mock(Facade.class);
		when(facade.findMagazineByAuthor(anyString())).thenThrow(ApplicationException.class);
		facade.findMagazineByAuthor("test");
	}

}
