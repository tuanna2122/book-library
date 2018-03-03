/**
 * This class is to wrap all check/uncheck exception
 */
package com.episerver.booklibrary.exception;

/**
 * @author Tuan Nguyen
 *
 */
public class ApplicationException extends RuntimeException {

	/**
	 * @serialField
	 */
	private static final long serialVersionUID = -185263038279685036L;

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

}
