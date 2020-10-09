/**
 * @author peripurnama
 * @since Jul 24, 2020
 * @email cisvapery@gmail.com
 * 
 */
package id.co.bankmandiri.user.response;

public class UserCreateResponse {

	private String firstname;
	private String lastname;
	private String email;
	private String userId;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
