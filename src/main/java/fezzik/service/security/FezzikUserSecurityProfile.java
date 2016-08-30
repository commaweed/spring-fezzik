package fezzik.service.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import fezzik.domain.User;

public class FezzikUserSecurityProfile implements UserDetails {


	private static final long serialVersionUID = -7921906433337948487L;

	private User user;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return this.user.getRoles();
//		AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
		return Collections.emptyList();
	}


	@Override
	public String getUsername() {
		String dn = "user name not set!";
		if (this.user != null) {
			dn = this.user.getUuid();
		}
		return dn;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	@Override public String getPassword() { return null; }
	@Override public boolean isAccountNonExpired() { return true; }
	@Override public boolean isAccountNonLocked() { return true; }
	@Override public boolean isCredentialsNonExpired() { return true; }
	@Override public boolean isEnabled() { return true; }

}