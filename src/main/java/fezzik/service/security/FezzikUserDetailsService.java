package fezzik.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fezzik.domain.User;
import fezzik.service.UserService;

@Service
public class FezzikUserDetailsService implements UserDetailsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FezzikUserDetailsService.class);
	
    @Autowired
    private UserService userService;

//	@Override
//	public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
//		LOGGER.info("Loading user details...");
//		X509Certificate certificate = (X509Certificate) token.getCredentials();
//		String dn = certificate.getSubjectX500Principal().getName();
//		LOGGER.info("User DN: " + dn);
//		
//		
//		User user = userService.getUser(dn);
//		if (user == null) {
//			user = new User(dn, dn, dn, dn);
//			userService.addUser(user);
//			LOGGER.info("Added user: " + user);
//		}
//		
//		LOGGER.info("Returning user: " + user);
//		
//		FezzikUserSecurityProfile userDetails = new FezzikUserSecurityProfile();
//		userDetails.setUser(user);
//
//		return userDetails;
//	}

	@Override
	public UserDetails loadUserByUsername(String dn) throws UsernameNotFoundException {
		LOGGER.info("return user details for ..." + dn);
		LOGGER.info("User DN: " + dn);
		
		
		User user = userService.getUser(dn);
		if (user == null) {
			user = new User(dn);
			userService.addUser(user);
			LOGGER.info("Added user: " + user);
		}
		
		LOGGER.info("Returning user: " + user);
		
		FezzikUserSecurityProfile userDetails = new FezzikUserSecurityProfile();
		userDetails.setUser(user);

		return (UserDetails) userDetails;
	}

}