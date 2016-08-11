package fezzik.web.controller.model;

/**
 * Represents a user's credentials.
 */
public class UserCredentials {
	
	private String userId;
	private String password;
	
	/**
	 * Default constructor required by spring using jackson to marshal post body.
	 */
	public UserCredentials() { }
	
	/**
	 * Initialize with the given credentials.
	 * @param userId The user's ID.
	 * @param password The user's password.
	 */
	public UserCredentials(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}