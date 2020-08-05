package ir.mine.project.base.authenticate.conditional.config.annotation.basic;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class BasicUserDetailService implements UserDetailsService {

	public BasicUserDetailService() {
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return new BasicUserDetails(username);
	}

}
