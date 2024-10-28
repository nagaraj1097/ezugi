package pojoutility;

public class RollBackPojo {

	public String operatorId;
	public String uid;
	public String transactionId;
	public int gameId;
	public String token;
	public double rollbackAmount;
	public int betTypeID;
	public int serverId;
	public String roundId;
	public String currency;
	public String seatId;
	public int platformId;
	public int tableId;
	public long timestamp;

	public RollBackPojo() {

	}

	public RollBackPojo(String operatorId, String uid, String transactionId, int gameId, String token,
			double rollbackAmount, int betTypeID, int serverId, String roundId, String currency, String seatId,
			int platformId, int tableId, long timestamp) {
		super();
		this.operatorId = operatorId;
		this.uid = uid;
		this.transactionId = transactionId;
		this.gameId = gameId;
		this.token = token;
		this.rollbackAmount = rollbackAmount;
		this.betTypeID = betTypeID;
		this.serverId = serverId;
		this.roundId = roundId;
		this.currency = currency;
		this.seatId = seatId;
		this.platformId = platformId;
		this.tableId = tableId;
		this.timestamp = timestamp;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public double getRollbackAmount() {
		return rollbackAmount;
	}

	public void setRollbackAmount(double rollbackAmount) {
		this.rollbackAmount = rollbackAmount;
	}

	public int getBetTypeID() {
		return betTypeID;
	}

	public void setBetTypeID(int betTypeID) {
		this.betTypeID = betTypeID;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public String getRoundId() {
		return roundId;
	}

	public void setRoundId(String roundId) {
		this.roundId = roundId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	public int getPlatformId() {
		return platformId;
	}

	public void setPlatformId(int platformId) {
		this.platformId = platformId;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
