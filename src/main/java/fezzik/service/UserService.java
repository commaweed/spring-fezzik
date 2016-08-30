package fezzik.service;

import java.util.List;

import fezzik.domain.User;
import fezzik.exception.FezzikDatabaseException;
import fezzik.exception.UserIdAlreadyExistsException;
import fezzik.exception.UserNotFoundException;

/**
 * Represents the business logic for interacting with the user repository.
 */
// TODO: alternatively we could return a 200 status code with success=false and an error message instead of the canned exceptions for certain things.
// Since the framework logs the errors, we'll need to change them to 200 with a message instead of exception (at controller layer that is)
public interface UserService {
	
	/**
	 * Adds the given user to the back-end system.
	 * @param user The user to add.
	 * @throws UserIdAlreadyExistsException If the ID of the user already exists in the back-end data system.
	 * @throws FezzikDatabaseException If an exception occurred while interacting with the back-end data system.
	 */
	void addUser(User user);
	
	/**
	 * Removes all users from the back-end data system.
	 * @throws FezzikDatabaseException If an exception occurred while interacting with the back-end data system.
	 */
	void removeAllUsers();

	/**
	 * Returns the user for the given userId or <code>null</code> if one was not found in the back-end system.
	 * @param userId The ID of the user.
	 * @return A valid user.
	 * @throws UserNotFoundException If the provided user ID does not exist in the back-end data system.
	 * @throws FezzikDatabaseException If an exception occurred while interacting with the back-end data system.
	 */
	User getUser(String userId);

	/**
	 * Returns information about the current user that made the request.
	 * @return A user or <code>null</code>.
	 * @throws FezzikDatabaseException If an exception occurred while interacting with the back-end data system.
	 */
	User getRequestUser();
	
	/**
	 * Returns information about the current user that is attempting to login.  If the user does not exist, a new user
	 * will be created.
	 * @param authenticationToken The authentication token provided by spring security.
	 * @return A user or <code>null</code>.
	 * @throws FezzikDatabaseException If an exception occurred while interacting with the back-end data system.
	 */
	User login(String authenticationToken);
	
	/**
	 * Returns all the users that currently exist in the back-end system.
	 * @return A collection of users that are in the back-end system or an empty collection if none exist.
	 * @throws FezzikDatabaseException If an exception occurred while interacting with the back-end data system.
	 */
	List<User> getAllUsers();
 

}
