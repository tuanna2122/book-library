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
import com.episerver.booklibrary.model.Magazine;
import com.episerver.booklibrary.utils.CommonUtils;
import com.episerver.booklibrary.utils.Constant;

/**
 * @author Tuan Nguyen
 *
 */
public class MagazineRepository {

	private String dsPath;

	private String authorDsPath;

	/**
	 * Apply singleton pattern
	 */
	private MagazineRepository(String dsPath, String authorDsPath) {
		this.dsPath = dsPath;
		this.authorDsPath = authorDsPath;
	}

	private static MagazineRepository instance = null;

	/**
	 * Map from a csv line to Magazine object
	 */
	private Function<String, Magazine> mapToMagazine = (line) -> {
		String[] data = line.split(Constant.SEMI_COLON);
		Magazine magazine = new Magazine();

		magazine.setTitle(CommonUtils.trim(data[0]));
		magazine.setIsbn(CommonUtils.trim(data[1]));

		Set<Author> authors = Arrays.stream(CommonUtils.trim(data[2]).split(Constant.COMMA)).map(e -> {
			Author author = AuthorRepository.getInstance(this.authorDsPath).findByEmail(e);
			return author;
		}).collect(Collectors.toSet());
		magazine.setAuthors(authors);

		magazine.setPublishDate(CommonUtils.trim(data[3]));

		return magazine;
	};

	public List<Magazine> getAllMagazines() throws DataAccessException {
		List<Magazine> magazines = new ArrayList<>();
		Class<MagazineRepository> clazz = MagazineRepository.class;

		try (InputStream is = clazz.getResourceAsStream(this.dsPath);
				BufferedReader br = new BufferedReader(new InputStreamReader(is, Constant.CHARSET_UTF_8));) {

			// The header line must be skipped
			magazines = br.lines().skip(1).map(mapToMagazine).collect(Collectors.toList());

		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(), e);
		}

		return magazines;
	}

	public static MagazineRepository getInstance(String dsPath, String authorDsPath) {
		if (instance == null) {
			instance = new MagazineRepository(dsPath, authorDsPath);
		}

		return instance;
	}

}
