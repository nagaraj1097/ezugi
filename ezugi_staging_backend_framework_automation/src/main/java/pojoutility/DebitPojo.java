package pojoutility;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class DebitPojo {

	public int gameId;
	public double debitAmount;
	public int platformId;
	public int serverId;
	public String transactionId;
	public String token;
	public String uid;
	public int betTypeID;
	public int tableId;
	public String seatId;
	public String currency;
	public String operatorId;
	public String roundId;
	public long timestamp;
	
	


	public DebitPojo() {

	}

	public DebitPojo(int gameId, double debitAmount, int platformId, int serverId, String transactionId, String token,
			String uid, int betTypeID, int tableId, String seatId, String currency, String operatorId, String roundId,
			long timestamp) {
		super();
		this.gameId = gameId;
		this.debitAmount = debitAmount;
		this.platformId = platformId;
		this.serverId = serverId;
		this.transactionId = transactionId;
		this.token = token;
		this.uid = uid;
		this.betTypeID = betTypeID;
		this.tableId = tableId;
		this.seatId = seatId;
		this.currency = currency;
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

	public double getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(double debitAmount) {
		this.debitAmount = debitAmount;
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
