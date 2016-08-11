package fezzik.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fezzik.domain.User;
import fezzik.exception.InvalidPasswordException;
import fezzik.exception.UserNotFoundException;
import fezzik.repository.UserRepository;

/**
 * The implementation of the service that interacts with the user repository.
 */
@Service
public class UserServiceImpl implements UserService {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
  
    /**
     * {@inheritDoc}
     */
	@Override
	public User getUser(String userId) {
		if (StringUtils.trimToNull(userId) == null) {
			throw new IllegalArgumentException("Invalid userId; it cannot be null.");
		}
		
		User user = userRepository.findOne(userId);
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
		List<User> allUsers = userRepository.findAll();
		return allUsers == null ? new ArrayList<>() : allUsers;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public boolean isValidLogin(String userId, String password) {
		User user = getUser(userId);
		
		if (!doesPasswordMatch(user, password)) {
			throw new InvalidPasswordException(userId);
		}
		
		return true;
	}
	
	/**
	 * Indicates whether or not the given password is correct for the given user.
	 * @param password The password to test.
	 * @param user The user record that was found in the database.
	 * @return
	 */
	private boolean doesPasswordMatch(User user, String password) {
		String encodedPassword = passwordEncoder.encode(password);
		return passwordEncoder.matches(user.getPassword(), encodedPassword);			
	}
}
