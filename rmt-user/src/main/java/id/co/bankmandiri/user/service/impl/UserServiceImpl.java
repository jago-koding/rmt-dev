/**
 * @author peripurnama
 * @since Jul 24, 2020
 * @email cisvapery@gmail.com
 * 
 */
package id.co.bankmandiri.user.service.impl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import feign.FeignException.FeignClientException;
import id.co.bankmandiri.user.client.AlbumServiceClient;
import id.co.bankmandiri.user.dto.UserDto;
import id.co.bankmandiri.user.entity.UserEntity;
import id.co.bankmandiri.user.repository.UserRepository;
import id.co.bankmandiri.user.request.UserCreateRequest;
import id.co.bankmandiri.user.response.AlbumResponse;
import id.co.bankmandiri.user.response.UserCreateResponse;
import id.co.bankmandiri.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserRepository userRepository;
	private BCryptPasswordEncoder passwordEncoder;
	private ModelMapper modelMapper;
	private RestTemplate restTemplate;
	private Environment environment;
	private AlbumServiceClient albumServiceClient;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder,
			ModelMapper modelMapper, RestTemplate restTemplate, Environment environment,
			AlbumServiceClient albumServiceClient) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.modelMapper = modelMapper;
		this.restTemplate = restTemplate;
		this.environment = environment;
		this.albumServiceClient = albumServiceClient;
		this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	@Override
	public UserDto create(UserDto userDto) {
		userDto.setUserId(UUID.randomUUID().toString());
		userDto.setEncryptedPassword(passwordEncoder.encode(userDto.getPassword()));

		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
		userEntity = userRepository.save(userEntity);
		return convertToUserDto(userEntity);
	}

	public UserEntity convertToUserEntity(UserDto userDto) {
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
		return userEntity;
	}

	public UserDto convertToUserDto(UserEntity userEntity) {
		UserDto userDto = modelMapper.map(userEntity, UserDto.class);
		return userDto;
	}

	public UserDto convertToUserDto(UserCreateRequest userCreateRequest) {
		UserDto userDto = modelMapper.map(userCreateRequest, UserDto.class);
		return userDto;
	}

	public UserCreateResponse convertToUserCreateResponse(UserDto userDto) {
		UserCreateResponse userCreateResponse = modelMapper.map(userDto, UserCreateResponse.class);
		return userCreateResponse;
	}

	@Override
	public UserDto findByEmail(String email) {
		UserEntity userEntity = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Email not found"));
		System.out.println("userEntity: " + userEntity.getEmail());
		return convertToUserDto(userEntity);
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new UsernameNotFoundException("User not found");

		UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);

//		String albumsUrl = String.format(environment.getProperty("albums.url"), userId);
//
//		ResponseEntity<List<AlbumResponse>> albumsListResponse = restTemplate.exchange(albumsUrl, HttpMethod.GET, null,
//				new ParameterizedTypeReference<List<AlbumResponse>>() {
//				});
//		List<AlbumResponse> albumsList = albumsListResponse.getBody();

        logger.info("Before calling albums Microservice");
//        List<AlbumResponse> albumsList = null;
//		try {
//			albumsList = albumServiceClient.getAlbumsByUserId(userId);
//		} catch (FeignClientException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		List<AlbumResponse> albumsList = albumServiceClient.getAlbumsByUserId(userId);
        logger.info("After calling albums Microservice");
		userDto.setAlbums(albumsList);

		return userDto;
	}
}
