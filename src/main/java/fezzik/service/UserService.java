package fezzik.service;

import java.util.List;

import fezzik.domain.User;
import fezzik.exception.InvalidPasswordException;
import fezzik.exception.UserNotFoundException;

/**
 * Represents the business logic for interacting with the user repository.
 */
// TODO: alternatively we could return a 200 status code with success=false and an error message instead of the canned exceptions for certain things.
// Since the framework logs the errors, we'll need to change them to 200 with a message instead of exception (at controller layer that is)
public interface UserService {

	/**
	 * Returns the user for the given userId or <code>null</code> if one was not found in the back-end system.
	 * @param userId The ID of the user.
	 * @return A user or <code>null</code>.
	 * @throws UserNotFoundException If the provided user ID does not exist in the back-end system.
	 */
	User getUser(String userId);
	
	/**
	 * Returns all the users that currently exist in the back-end system.
	 * @return A collection of users that are in the back-end system or an empty collection if none exist.
	 */
	List<User> getAllUsers();
	
	/**
	 * Validates the given user credentials against the backend data store.  Since this is a rest service, we are going to use
	 * exceptions and an exception handler to handle the validation of logins.
	 * @param userId The id of the user.
	 * @param password The unencrypted password of the user.
	 * @return <code>true</code> if and only if the given credentials are valid.
	 * @throws InvalidPasswordException If the user exists in the backend data store but the password does not match.
	 * @throws UserNotFoundException If the provided user ID does not exist in the back-end system.
	 */
	boolean isValidLogin(String userId, String password);
 

}
