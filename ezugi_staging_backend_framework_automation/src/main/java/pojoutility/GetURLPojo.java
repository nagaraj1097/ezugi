package pojoutility;

public class GetURLPojo {

	public String playerId;
	public int platformId;
	public String operatorId;
	public String token;
	public String currencyCode;
	public long timestamp;

	public GetURLPojo() {

	}

	public GetURLPojo(String playerId, int platformId, String operatorId, String token, String currencyCode,
			long timestamp) {
		super();
		this.playerId = playerId;
		this.platformId = platformId;
		this.operatorId = operatorId;
		this.token = token;
		this.currencyCode = currencyCode;
		this.timestamp = timestamp;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
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

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
