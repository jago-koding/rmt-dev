/**
 * @author peripurnama
 * @since Jul 26, 2020
 * @email cisvapery@gmail.com
 * 
 */
package id.co.bankmandiri.user.client.fallback_factory;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import feign.FeignException;
import feign.hystrix.FallbackFactory;
import id.co.bankmandiri.user.client.AlbumServiceClient;
import id.co.bankmandiri.user.response.AlbumResponse;

@Component
public class AlbumFallbackFactory implements FallbackFactory<AlbumServiceClient> {

	@Override
	public AlbumServiceClient create(Throwable cause) {
		return new AlbumServiceClientFallback(cause);
	}

}

class AlbumServiceClientFallback implements AlbumServiceClient {

	private static final Logger log = LoggerFactory.getLogger(AlbumServiceClientFallback.class);

	private final Throwable cause;

	public AlbumServiceClientFallback(Throwable cause) {
		this.cause = cause;
	}

	@Override
	public List<AlbumResponse> getAlbumsByUserId(String userId) {
		if (cause instanceof FeignException) {
			if (((FeignException) cause).status() == 404) {
				log.error("404 error not found album with user id: {} ".concat(cause.getLocalizedMessage()), userId);
			}
		} else {
			log.error("Exception album with user id: {} ".concat(cause.getLocalizedMessage()), userId);
		}
		return new ArrayList<>();
	}

}