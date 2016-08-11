package fezzik.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * Represents the type of exception that will be thrown when a user logs in and a record is found but the passwords
 * do not match.
 */
public class UserNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -2413316337120292386L;

	/**
	 * Initialize with the given userId.
	 * @param userId The ID of the user that was used in a query against existing users.
	 */
	public UserNotFoundException(String userId) {
		super(
			String.format(
				"The given user was not found in the database: [%s]", 
				StringUtils.trimToNull(userId) == null ? "userId was null or empty" : userId
			)
		);
	}
	
}