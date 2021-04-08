package br.com.sgm.auth.model.dto;

public class UserTokenDTO {

	private String token;
	private UserDTO user;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

}
