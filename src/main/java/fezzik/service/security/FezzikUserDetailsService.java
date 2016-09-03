package fezzik.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fezzik.domain.PkiUser;
import fezzik.service.PkiUserService;

@Service
public class FezzikUserDetailsService implements UserDetailsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FezzikUserDetailsService.class);
	
    @Autowired
    private PkiUserService userService;

	@Override
	public UserDetails loadUserByUsername(String authenticationToken) throws UsernameNotFoundException {
		LOGGER.info("Attempting to loadUserByUsername: " + authenticationToken);
		PkiUser user = userService.login(authenticationToken);
		FezzikUserSecurityProfile userDetails = new FezzikUserSecurityProfile();
		userDetails.setUser(user);
		return (UserDetails) userDetails;
	}

}