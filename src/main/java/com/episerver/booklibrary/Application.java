/**
 * 
 */
package com.episerver.booklibrary;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.episerver.booklibrary.model.Author;

/**
 * @author Tuan Nguyen
 *
 */
public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Optional<Author> optional = Optional.ofNullable(null);
		if (optional.isPresent()) {
			System.out.println("OK");
		}
		optional.ifPresent(System.out::println);
		Arrays.stream(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }).parallel().forEach(System.out::println);
		Arrays.stream(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }).min().orElse(0);
		Map<String, List<String>> people = new HashMap<>();
		people.put("John", Arrays.asList("555-1123", "555-3389"));
		people.put("Mary", Arrays.asList("555-2243", "555-5264"));
		people.put("Steve", Arrays.asList("555-6654", "555-3242"));

		people.values().stream().flatMap(Collection::stream).collect(Collectors.toList()).forEach(System.out::println);
	}

}
