package pojoutility;

public class BalancePojo {

	private String uid;
	private int platformId;
	private String operatorId;
	private long timestamp;

	public BalancePojo() {

	}

	public BalancePojo(String uid, int platformId, String operatorId, long timestamp) {
		super();
		this.uid = uid;
		this.platformId = platformId;
		this.operatorId = operatorId;
		this.timestamp = timestamp;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
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

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
