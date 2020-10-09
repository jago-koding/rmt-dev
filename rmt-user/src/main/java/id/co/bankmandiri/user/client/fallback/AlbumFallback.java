/**
 * @author peripurnama
 * @since Jul 26, 2020
 * @email cisvapery@gmail.com
 * 
 */
package id.co.bankmandiri.user.client.fallback;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import id.co.bankmandiri.user.client.AlbumServiceClient;
import id.co.bankmandiri.user.response.AlbumResponse;

@Component
public class AlbumFallback implements AlbumServiceClient{

	@Override
	public List<AlbumResponse> getAlbumsByUserId(String userId) {
		return new ArrayList<>();
	}

}
