/**
 * 
 */
package com.episerver.booklibrary.exception;

/**
 * @author Tuan Nguyen
 *
 */
public class ServiceException extends RuntimeException {

	/**
	 * @serialField
	 */
	private static final long serialVersionUID = 8589833388732668906L;

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
