/**
 * 
 */
package com.episerver.booklibrary.utils;

/**
 * @author Tuan Nguyen
 *
 */
public final class CommonUtils {

	/**
	 * In order to prevent other to initialize it. It should be used in static way
	 */
	private CommonUtils() {
	}

	public static String trim(String str) {
		if (null == str || "".equals(str)) {
			return "";
		}

		return str.trim();
	}

}
