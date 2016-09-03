package fezzik.service;

import java.util.List;

import fezzik.domain.PkiUser;
import fezzik.exception.FezzikDatabaseException;
import fezzik.exception.UserIdAlreadyExistsException;
import fezzik.exception.UserNotFoundException;

/**
 * Represents the business logic for interacting with the user repository when user's use the 2-way ssl login.
 */
public interface PkiUserService {
	
	/**
	 * Adds the given user to the back-end system.
	 * @param user The user to add.
	 * @throws UserIdAlreadyExistsException If the ID of the user already exists in the back-end data system.
	 * @throws FezzikDatabaseException If an exception occurred while interacting with the back-end data system.
	 */
	void addUser(PkiUser user);
	
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
	PkiUser getUser(String userId);

	/**
	 * Returns information about the current user that made the request.
	 * @return A user or <code>null</code>.
	 * @throws FezzikDatabaseException If an exception occurred while interacting with the back-end data system.
	 */
	PkiUser getRequestUser();
	
	/**
	 * Returns information about the current user that is attempting to login.  If the user does not exist, a new user
	 * will be created.
	 * @param authenticationToken The authentication token provided by spring security.
	 * @return A user or <code>null</code>.
	 * @throws FezzikDatabaseException If an exception occurred while interacting with the back-end data system.
	 */
	PkiUser login(String authenticationToken);
	
	/**
	 * Returns all the users that currently exist in the back-end system.
	 * @return A collection of users that are in the back-end system or an empty collection if none exist.
	 * @throws FezzikDatabaseException If an exception occurred while interacting with the back-end data system.
	 */
	List<PkiUser> getAllUsers();


}
