/**
 * 
 */
package com.episerver.booklibrary.service;

import java.util.List;

import com.episerver.booklibrary.exception.ServiceException;
import com.episerver.booklibrary.model.Magazine;
import com.episerver.booklibrary.repository.MagazineRepository;

/**
 * @author Tuan Nguyen
 *
 */
public class MagazineService {

	private MagazineRepository repository;

	private static MagazineService instance = null;

	/**
	 * Apply singleton pattern
	 */
	private MagazineService(String dsPath, String authorDsPath) {
		repository = MagazineRepository.getInstance(dsPath, authorDsPath);
	}

	public List<Magazine> getAllMagazines() throws ServiceException {
		try {
			return this.repository.getAllMagazines();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public static MagazineService getInstance(String dsPath, String authorDsPath) {
		if (instance == null) {
			instance = new MagazineService(dsPath, authorDsPath);
		}

		return instance;
	}

}
