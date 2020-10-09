package id.co.bankmandiri.user.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import id.co.bankmandiri.user.entity.UserEntity;

public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 4313653395184670905L;

	@JsonIgnore
	private String userId;
	
	@JsonIgnore
	private String email;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal() {
		super();
	}

	public UserPrincipal(String userId, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDetails create(UserEntity m){
		List<GrantedAuthority> authorities = new ArrayList<>();
//		List<GrantedAuthority> authorities = m.getRoles().stream().map(role ->
//        new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		
    	return new UserPrincipal(
    			m.getUserId(),
                m.getEmail(),
                m.getEncryptedPassword(),
                authorities
        );
    }

	public String getUserId() {
		return userId;
	}
	
	public String getEmail() {
		return email;
	}

	
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public String toString() {
		return "UserPrincipal [userId=" + userId + ", email=" + email + ", password=" + password + ", authorities="
				+ authorities + "]";
	}
	
	
}