package pojoutility;

public class AuthenticationPojo {

	public int platformId;
	public String operatorId;
	public String token;
	public long timestamp;

	public AuthenticationPojo() {

	}

	public AuthenticationPojo(int platformId, String operatorId, String token, long timestamp) {
		super();
		this.platformId = platformId;
		this.operatorId = operatorId;
		this.token = token;
		this.timestamp = timestamp;
	}

	public int getPlatformId() {
		return platformId;
	}

	public void setPlatformId(int platformId) {
		this.platformId = platformId;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
