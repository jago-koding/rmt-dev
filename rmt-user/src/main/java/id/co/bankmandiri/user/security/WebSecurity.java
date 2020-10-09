/**
 * @author peripurnama
 * @since Jul 24, 2020
 * @email cisvapery@gmail.com
 * 
 */
package id.co.bankmandiri.user.security;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import id.co.bankmandiri.user.config.CustomUserDetailsService;
import id.co.bankmandiri.user.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment environment;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/**").hasIpAddress(environment.getProperty("gateway.ip"))
		.and()
		.addFilter(getAuthenticationFilter());
		http.headers().frameOptions().disable();
	}
	
	private AuthenticationFilter getAuthenticationFilter() throws Exception
	{
		AuthenticationFilter authenticationFilter = new AuthenticationFilter();
		authenticationFilter.setAuthenticationManager(authenticationManager()); 
		authenticationFilter.setEnvironment(environment);
		authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
		return authenticationFilter;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
