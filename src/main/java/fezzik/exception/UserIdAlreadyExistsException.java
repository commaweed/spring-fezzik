package fezzik.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * Represents the type of exception that will be thrown if a user ID already exists in the database
 * whenever an insert operation is performed.
 */
public class UserIdAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = -8884470638011883498L;

	/**
	 * Initialize with the given userId; while it does except a <code>null</code> user, ensure it is not.
	 * @param userId The ID of the user that had the invalid password.
	 */
	public UserIdAlreadyExistsException(String userId) {
		super(
			String.format(
				"Invalid user ID; it already exists in the database: [%s]", 
				StringUtils.trimToNull(userId) == null ? "userId was null or empty" : userId
			)
		);
	}
	
}