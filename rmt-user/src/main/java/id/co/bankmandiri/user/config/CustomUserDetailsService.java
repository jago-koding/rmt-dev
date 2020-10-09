package id.co.bankmandiri.user.config;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import id.co.bankmandiri.user.entity.UserEntity;
import id.co.bankmandiri.user.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByEmail(usernameOrEmail).orElseThrow(
				() -> new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail));
		if (user != null) {
			return UserPrincipal.create(user);
		}

		throw new UsernameNotFoundException("User '" + usernameOrEmail + "' not found");
	}

}