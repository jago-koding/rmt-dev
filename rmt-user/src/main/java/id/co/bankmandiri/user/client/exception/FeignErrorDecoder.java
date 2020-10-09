/**
 * @author peripurnama
 * @since Jul 26, 2020
 * @email cisvapery@gmail.com
 * 
 */
package id.co.bankmandiri.user.client.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {

	private static final Logger logger = LoggerFactory.getLogger(FeignErrorDecoder.class);

	@Override
	public Exception decode(String methodKey, Response response) {
		logger.info("CALL methodKey: {}", methodKey);
		logger.info("Response status: {}", response.status());
		switch (response.status()) {
		case 400:
			break;
		case 404:
			if(methodKey.contains("getAlbumsByUserId")) {
				return new ResponseStatusException(HttpStatus.valueOf(response.status()), "User album not found!");
			}
			break;
		default:
			return new Exception(response.reason());
		}
		return null;
	}

}
