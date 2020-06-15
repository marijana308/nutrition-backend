package diplomski.nutrition.dto;

public class TokenDTO {
	
	public TokenDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TokenDTO(String token) {
		super();
		this.token = token;
	}

	String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
