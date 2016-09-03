package fezzik.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fezzik.domain.User;
import fezzik.exception.FezzikDatabaseException;
import fezzik.exception.InvalidPasswordException;
import fezzik.exception.UserNotFoundException;
import fezzik.repository.UserRepository;

/**
 * The implementation of the service that interacts with the user repository.
 */
@Service
@Profile("basicauth")
public class BasicAuthUserServiceImpl implements BasicAuthUserService {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(BasicAuthUserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;
    
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
		
		// encrypt the password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		try {
			user = userRepository.save(user);
		} catch (Exception e) {
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
			throw new FezzikDatabaseException("Unable to remove all users due to error", e);
		} 	
	}
  
    /**
     * {@inheritDoc}
     */
	@Override
	public User getUser(String userId) {
		if (StringUtils.trimToNull(userId) == null) {
			throw new IllegalArgumentException("Invalid userId; it cannot be null.");
		}
		
		User user;
		try {
			user = userRepository.findOne(userId);
		} catch (Exception e) {
			throw new FezzikDatabaseException("Unable to get user [" + userId + "] due to error", e);
		}
		
		if (user == null) {
			throw new UserNotFoundException(userId);
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
			throw new FezzikDatabaseException("Unable to retrieve all users due to error", e);
		}
		
		return allUsers == null ? new ArrayList<>() : allUsers;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public boolean isValidLogin(String userId, String password) {
		User user = getUser(userId);
		
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new InvalidPasswordException(userId);
		}
		
		return true;
	}

}
