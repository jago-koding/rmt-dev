/**
 * @author peripurnama
 * @since Jul 26, 2020
 * @email cisvapery@gmail.com
 * 
 */
package id.co.bankmandiri.user.response;

public class AlbumResponse {

	private String albumId;
	private String userId;
	private String name;
	private String description;

	public String getAlbumId() {
		return albumId;
	}

	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
