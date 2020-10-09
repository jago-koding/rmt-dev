/**
 * @author peripurnama
 * @since Jul 26, 2020
 * @email cisvapery@gmail.com
 * 
 */
package id.co.bankmandiri.user.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import id.co.bankmandiri.user.client.fallback_factory.AlbumFallbackFactory;
import id.co.bankmandiri.user.response.AlbumResponse;

@FeignClient(name = "albums-ws", fallbackFactory = AlbumFallbackFactory.class) //fallback = AlbumFallback.class)
public interface AlbumServiceClient {

	@GetMapping("/users/{id}/albums")
	public List<AlbumResponse> getAlbumsByUserId(@PathVariable(name = "id") String userId);
	
}
