package com.test.performance.result;

public class Result {
	
	private long startTime;
	private String trackingId;
	private boolean result;
	private long consumeTime;
	
	public Result(long startTime, String trackingId, boolean result, long consumeTime) {
		super();
		this.startTime = startTime;
		this.trackingId = trackingId;
		this.result = result;
		this.consumeTime = consumeTime;
	}
	/**
	 * @return the trackingId
	 */
	public String getTrackingId() {
		return trackingId;
	}
	/**
	 * @return the result
	 */
	public boolean isResult() {
		return result;
	}
	/**
	 * @return the consumeTime
	 */
	public long getConsumeTime() {
		return consumeTime;
	}
	/**
	 * @param trackingId the trackingId to set
	 */
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(boolean result) {
		this.result = result;
	}
	/**
	 * @param consumeTime the consumeTime to set
	 */
	public void setConsumeTime(long consumeTime) {
		this.consumeTime = consumeTime;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResultRecord [startTime=");
		builder.append(startTime);
		builder.append("&trackingId=");
		builder.append(trackingId);
		builder.append("&result=");
		builder.append(result);
		builder.append("&consumeTime=");
		builder.append(consumeTime);
		builder.append("]");
		return builder.toString();
	}
	
	 
	
 
}
