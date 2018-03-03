/**
 * 
 */
package com.episerver.booklibrary;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.episerver.booklibrary.facade.Facade;
import com.episerver.booklibrary.model.BaseModel;

/**
 * @author Tuan Nguyen
 *
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Facade facade = Facade.getInstance("/authors.csv", "/books.csv", "/magazines.csv");
		Scanner scanner = new Scanner(System.in);

		System.out.println("Please choose (from 1 - 8 or 'q' to quit): ");
		System.out.println("1. Reading all data (authors, books, magazines) from system.");
		System.out.println("2. Print out all details of all books and magazines.");
		System.out.println("3. Find and print out the details of a book with an ISBN");
		System.out.println("4. Find and print out the details of a magazine with an ISBN");
		System.out.println("5. Find and print out the details of a book for an author");
		System.out.println("6. Find and print out the details of a magazine for an author");
		System.out.println("7. Sort all books and magazine by title and print out the result");
		System.out.println("8. Quit");

		String input = scanner.nextLine();

		switch (input) {
		case "1":
			readAll(facade, false, false);
			break;
		case "2":
			readAll(facade, true, false);
			break;
		case "3":
			System.out.print("Please enter book's ISBN: ");
			String bisbn = scanner.nextLine();
			System.out.println(facade.findBookByIsbn(bisbn));
			break;
		case "4":
			System.out.print("Please enter magazine's ISBN: ");
			String misbn = scanner.nextLine();
			System.out.println(facade.findMagazineByIsbn(misbn));
			break;
		case "5":
			System.out.print("Please enter author's email: ");
			String bAuthor = scanner.nextLine();
			System.out.println(facade.findBookByAuthor(bAuthor));
			break;
		case "6":
			System.out.print("Please enter author's email: ");
			String mAuthor = scanner.nextLine();
			System.out.println(facade.findMagazineByAuthor(mAuthor));
			break;
		case "7":
			readAll(facade, true, true);
			break;
		case "8":
			break;
		case "q":
			break;
		default:
			System.out.println("Please enter a valid option!");
			break;
		}

		scanner.close();

	}

	private static void readAll(Facade facade, boolean excludeAuthors, boolean sort) {
		Map<String, List<? extends BaseModel>> all = null;
		if (excludeAuthors) {
			all = facade.readAllBooksAndMagazines(sort);
		} else {
			all = facade.readAllData();
		}

		all.forEach((k, v) -> {
			System.out.println("\nAll Available " + k.toUpperCase() + "\n");
			v.forEach(System.out::println);
		});
	}

}
