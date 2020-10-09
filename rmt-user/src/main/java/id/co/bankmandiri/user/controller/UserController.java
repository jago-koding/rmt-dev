/**
 * @author peripurnama
 * @since Jul 24, 2020
 * @email cisvapery@gmail.com
 * 
 */
package id.co.bankmandiri.user.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.bankmandiri.user.dto.UserDto;
import id.co.bankmandiri.user.request.UserCreateRequest;
import id.co.bankmandiri.user.response.UserCreateResponse;
import id.co.bankmandiri.user.response.UserResponse;
import id.co.bankmandiri.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;

	private Environment environment;

	@Autowired
	public UserController(UserService userService, Environment environment) {
		super();
		this.userService = userService;
		this.environment = environment;
	}

	@GetMapping("/status/check")
	public String status() {
		return "working on port - " + environment.getProperty("local.server.port") + ", with token secret - "
				+ environment.getProperty("token.secret");
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> create(@Valid @RequestBody UserCreateRequest userCreateRequest) {
		UserDto userDto = userService.convertToUserDto(userCreateRequest);
		userDto = userService.create(userDto);
		UserCreateResponse body = userService.convertToUserCreateResponse(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(body);
	}
	
	@GetMapping(value="/{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<UserResponse> getUser(@PathVariable("userId") String userId) {
       
        UserDto userDto = userService.getUserByUserId(userId); 
        UserResponse returnValue = new ModelMapper().map(userDto, UserResponse.class);
        
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
}
