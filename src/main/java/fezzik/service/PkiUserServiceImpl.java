package fezzik.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import fezzik.domain.PkiUser;
import fezzik.exception.FezzikDatabaseException;
import fezzik.exception.UserNotFoundException;
import fezzik.repository.PkiUserRepository;

/**
 * The implementation of the service that interacts with the user repository for users that login with
 * 2-way ssl.
 */
@Service
@Profile("pki")
public class PkiUserServiceImpl implements PkiUserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PkiUserServiceImpl.class);

    @Autowired
    private PkiUserRepository userRepository;
        
    /**
     * {@inheritDoc}
     */
	@Override
	public void addUser(PkiUser user) {
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
	private PkiUser getDatabaseUser(String userId) {
		PkiUser user = null;
		
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
	public PkiUser getUser(String userId) {
		if (StringUtils.trimToNull(userId) == null) {
			throw new IllegalArgumentException("Invalid userId; it cannot be null.");
		}
		
		PkiUser user = getDatabaseUser(userId);
		if (user == null) {
			throw new UserNotFoundException(userId);
		}

		return user;
	} 
	
    /**
     * {@inheritDoc}
     */
	@Override
	public PkiUser getRequestUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userId = auth == null ? null : auth.getName(); 
		
		PkiUser user = getDatabaseUser(userId);
		if (user == null) {
			throw new IllegalStateException("Expected user not found in database [" + userId + "]!");
		}
		
		return getDatabaseUser(userId);
	} 
	
    /**
     * {@inheritDoc}
     */
	@Override
	public PkiUser login(String authenticationToken) {
		PkiUser user = getDatabaseUser(authenticationToken);
		
		if (user == null) {
			user = new PkiUser(authenticationToken);
			this.addUser(user);
			LOGGER.debug("Added user: " + user);
		}
		
		return user;
	}	
    
    /**
     * {@inheritDoc}
     */
	@Override
	public List<PkiUser> getAllUsers() {
		List<PkiUser> allUsers;
		
		try {
			allUsers = userRepository.findAll();
		} catch (Exception e) {
			LOGGER.error("Unable to retrieve all users due to error", e);
			throw new FezzikDatabaseException("Unable to retrieve all users due to error", e);
		}
		
		return allUsers == null ? new ArrayList<>() : allUsers;
	}
	
}
