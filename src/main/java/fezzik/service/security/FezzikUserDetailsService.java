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

	@Override
	public UserDetails loadUserByUsername(String authenticationToken) throws UsernameNotFoundException {
		User user = userService.login(authenticationToken);
		FezzikUserSecurityProfile userDetails = new FezzikUserSecurityProfile();
		userDetails.setUser(user);
		return (UserDetails) userDetails;
	}

}