package fezzik.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * Represents the type of exception that will be thrown when a user logs in and a record is found but the passwords
 * do not match.
 */
// TODO: determine if passwords should expire after a certain date, and if so, change this to accomodate that type of exception as well.
public class InvalidPasswordException extends RuntimeException {
	
	private static final long serialVersionUID = 859667638199322175L;
	
	/**
	 * Initialize with the given userId; while it does except a <code>null</code> user, ensure it is not.
	 * @param userId The ID of the user that had the invalid password.
	 */
	public InvalidPasswordException(String userId) {
		super(
			String.format(
				"The password did not match for the given user: [%s]", 
				StringUtils.trimToNull(userId) == null ? "userId was null or empty" : userId
			)
		);
	}
	
}