/**
 * @author peripurnama
 * @since Jul 27, 2020
 * @email cisvapery@gmail.com
 * 
 */
package id.co.bankmandiri.dicovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private Environment environment;
	
	@Autowired
	public WebSecurity(Environment environment) {
		super();
		this.environment = environment;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	PasswordEncoder encoder = 
          PasswordEncoderFactories.createDelegatingPasswordEncoder();
    	auth
          .inMemoryAuthentication()
          .withUser(environment.getProperty("eureka.username"))
          .password(encoder.encode(environment.getProperty("eureka.password")))
          .roles("USER");
    }
}
