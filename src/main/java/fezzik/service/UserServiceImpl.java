package fezzik.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fezzik.domain.User;
import fezzik.exception.FezzikDatabaseException;
import fezzik.exception.UserNotFoundException;
import fezzik.repository.UserRepository;

/**
 * The implementation of the service that interacts with the user repository.
 */
@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;
    
    @SuppressWarnings("unused")
	@Autowired
    private PasswordEncoder passwordEncoder;
    
    /**
     * {@inheritDoc}
     */
	@Override
	public void addUser(User user) {
		if (user == null) {
			throw new IllegalArgumentException("Invalid user; it cannot be null!");
		}
		user.validate();
		
		try {
			user = userRepository.save(user);
		} catch (Exception e) {
			LOGGER.error("Unable to add user due to error: " + user, e);
			throw new FezzikDatabaseException("Unable to add user due to error: " + user, e);
		} 	
	}
	
    /**
     * {@inheritDoc}
     */
	@Override
	public void removeAllUsers() {
		try {
			userRepository.deleteAll();
		} catch (Exception e) {
			LOGGER.error("Unable to remove all users due to error", e);
			throw new FezzikDatabaseException("Unable to remove all users due to error", e);
		} 	
	}
	
	/**
	 * Returns the given user from the database.
	 * @param userId The ID of the user.
	 * @return A valid User or <code>null</code>.
	 */
	private User getDatabaseUser(String userId) {
		User user = null;
		
		if (userId != null) {
			try {
				user = userRepository.findOne(userId);
			} catch (Exception e) {
				LOGGER.error("Unable to get user [" + userId + "] due to error", e);
				throw new FezzikDatabaseException("Unable to get user [" + userId + "] due to error", e);
			}
		}
		
		return user;
	}
  
    /**
     * {@inheritDoc}
     */
	@Override
	public User getUser(String userId) {
		if (StringUtils.trimToNull(userId) == null) {
			throw new IllegalArgumentException("Invalid userId; it cannot be null.");
		}
		
		User user = getDatabaseUser(userId);
		if (user == null) {
			throw new UserNotFoundException(userId);
		}

		return user;
	} 
	
    /**
     * {@inheritDoc}
     */
	@Override
	public User getRequestUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userId = auth == null ? null : auth.getName(); 
		
		User user = getDatabaseUser(userId);
		if (user == null) {
			throw new IllegalStateException("Expected user not found in database [" + userId + "]!");
		}
		
		return getDatabaseUser(userId);
	} 
	
    /**
     * {@inheritDoc}
     */
	@Override
	public User login(String authenticationToken) {
		User user = getDatabaseUser(authenticationToken);
		
		if (user == null) {
			user = new User(authenticationToken);
			this.addUser(user);
			LOGGER.debug("Added user: " + user);
		}
		
		return user;
	}	
    
    /**
     * {@inheritDoc}
     */
	@Override
	public List<User> getAllUsers() {
		List<User> allUsers;
		
		try {
			allUsers = userRepository.findAll();
		} catch (Exception e) {
			LOGGER.error("Unable to retrieve all users due to error", e);
			throw new FezzikDatabaseException("Unable to retrieve all users due to error", e);
		}
		
		return allUsers == null ? new ArrayList<>() : allUsers;
	}



}
