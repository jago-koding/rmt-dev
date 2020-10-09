/**
 * @author peripurnama
 * @since Jul 24, 2020
 * @email cisvapery@gmail.com
 * 
 */
package id.co.bankmandiri.user.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.bankmandiri.user.config.UserPrincipal;
import id.co.bankmandiri.user.dto.UserDto;
import id.co.bankmandiri.user.entity.UserEntity;
import id.co.bankmandiri.user.request.LoginRequest;
import id.co.bankmandiri.user.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private Environment environment;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
            LoginRequest creds = new ObjectMapper()
                    .readValue(request.getInputStream(), LoginRequest.class);
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		String userId = ((UserPrincipal) authResult.getPrincipal()).getUserId();
		System.out.println(((UserPrincipal) authResult.getPrincipal()));
        String token = Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret") )
                .compact();
        
        response.addHeader("token", token);
        response.addHeader("userId", userId);
        System.out.println("TOKEN: " + token);
        System.out.println("USERID: " + userId);
	}
	
	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
}
