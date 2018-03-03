/**
 * 
 */
package com.episerver.booklibrary.exception;

/**
 * @author Tuan Nguyen
 *
 */
public class DataAccessException extends RuntimeException {

	/**
	 * @serialField
	 */
	private static final long serialVersionUID = -8929506316128572506L;

	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

}
