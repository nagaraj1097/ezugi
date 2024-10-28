package pojoutility;

public class CreditPojo {

	public int gameId;
	public String debitTransactionId;
	public boolean isEndRound;
	public String creditIndex;
	public String gameDataString;
	public int platformId;
	public int serverId;
	public String transactionId;
	public String token;
	public String uid;
	public int returnReason;
	public int betTypeID;
	public int tableId;
	public String seatId;
	public String currency;
	public double creditAmount;
	public String operatorId;
	public String roundId;
	public long timestamp;

	public CreditPojo() {

	}

	public CreditPojo(int gameId, String debitTransactionId, boolean isEndRound, String creditIndex,
			String gameDataString, int platformId, int serverId, String transactionId, String token, String uid,
			int returnReason, int betTypeID, int tableId, String seatId, String currency, double creditAmount,
			String operatorId, String roundId, long timestamp) {
		super();
		this.gameId = gameId;
		this.debitTransactionId = debitTransactionId;
		this.isEndRound = isEndRound;
		this.creditIndex = creditIndex;
		this.gameDataString = gameDataString;
		this.platformId = platformId;
		this.serverId = serverId;
		this.transactionId = transactionId;
		this.token = token;
		this.uid = uid;
		this.returnReason = returnReason;
		this.betTypeID = betTypeID;
		this.tableId = tableId;
		this.seatId = seatId;
		this.currency = currency;
		this.creditAmount = creditAmount;
		this.operatorId = operatorId;
		this.roundId = roundId;
		this.timestamp = timestamp;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getDebitTransactionId() {
		return debitTransactionId;
	}

	public void setDebitTransactionId(String debitTransactionId) {
		this.debitTransactionId = debitTransactionId;
	}

	public boolean isEndRound() {
		return isEndRound;
	}

	public void setEndRound(boolean isEndRound) {
		this.isEndRound = isEndRound;
	}

	public String getCreditIndex() {
		return creditIndex;
	}

	public void setCreditIndex(String creditIndex) {
		this.creditIndex = creditIndex;
	}

	public String getGameDataString() {
		return gameDataString;
	}

	public void setGameDataString(String gameDataString) {
		this.gameDataString = gameDataString;
	}

	public int getPlatformId() {
		return platformId;
	}

	public void setPlatformId(int platformId) {
		this.platformId = platformId;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getReturnReason() {
		return returnReason;
	}

	public void setReturnReason(int returnReason) {
		this.returnReason = returnReason;
	}

	public int getBetTypeID() {
		return betTypeID;
	}

	public void setBetTypeID(int betTypeID) {
		this.betTypeID = betTypeID;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getRoundId() {
		return roundId;
	}

	public void setRoundId(String roundId) {
		this.roundId = roundId;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
