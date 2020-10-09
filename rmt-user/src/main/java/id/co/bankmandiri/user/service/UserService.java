/**
 * @author peripurnama
 * @since Jul 24, 2020
 * @email cisvapery@gmail.com
 * 
 */
package id.co.bankmandiri.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import id.co.bankmandiri.user.dto.UserDto;
import id.co.bankmandiri.user.entity.UserEntity;
import id.co.bankmandiri.user.request.UserCreateRequest;
import id.co.bankmandiri.user.response.UserCreateResponse;

public interface UserService {

	UserDto create(UserDto userDto);
	UserDto findByEmail(String email);
	UserDto getUserByUserId(String userId);
	
	//converter
	UserEntity convertToUserEntity(UserDto userDto);
	UserDto convertToUserDto(UserEntity userEntity);
	UserDto convertToUserDto(UserCreateRequest userCreateRequest);
	UserCreateResponse convertToUserCreateResponse(UserDto userDto);
	
}
