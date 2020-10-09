/**
 * @author peripurnama
 * @since Jul 24, 2020
 * @email cisvapery@gmail.com
 * 
 */
package id.co.bankmandiri.user.dto;

import java.io.Serializable;
import java.util.List;

import id.co.bankmandiri.user.response.AlbumResponse;

public class UserDto implements Serializable {

	private static final long serialVersionUID = -4147790555449251661L;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String encryptedPassword;
	private String userId;
	private List<AlbumResponse> albums;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<AlbumResponse> getAlbums() {
		return albums;
	}

	public void setAlbums(List<AlbumResponse> albums) {
		this.albums = albums;
	}

}
