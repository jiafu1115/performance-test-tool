package com.test.performance.result;

public class PerformanceResult {
	
	private long startTime;
	private String trackingId;
	private long consumeTimeInMillis;
	private boolean isSuccess;
	private String message;
  	
	public PerformanceResult(String trackingId, boolean isSuccess, String message, long startTime, long consumeTime) {
		this.startTime = startTime;
		this.trackingId = trackingId;
		this.consumeTimeInMillis = consumeTime;
		this.isSuccess = isSuccess;
		this.message = 	message;
	}
 
	/**
	 * @return the consumeTimeInMillis
	 */
	public long getConsumeTimeInMillis() {
		return consumeTimeInMillis;
	}

	/**
	 * @param consumeTimeInMillis the consumeTimeInMillis to set
	 */
	public void setConsumeTimeInMillis(long consumeTimeInMillis) {
		this.consumeTimeInMillis = consumeTimeInMillis;
	}

	/**
	 * @return the trackingId
	 */
	public String getTrackingId() {
		return trackingId;
	}
 
 
	/**
	 * @param trackingId the trackingId to set
	 */
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}
	 
 
	/**
	 * @return the startTime
	 */
	public long getStartTime() {
		return startTime;
	}
	 
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the isSuccess
	 */
	public boolean isSuccess() {
		return isSuccess;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param isSuccess the isSuccess to set
	 */
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PerformanceResult [trackingId=");
		builder.append(trackingId);
		builder.append("&isSuccess=");
		builder.append(isSuccess);
		builder.append("&message=");
		builder.append(message);
		builder.append("&startTime=");
		builder.append(startTime);
		builder.append("&consumeTimeInMillis=");
		builder.append(consumeTimeInMillis);
		builder.append("]");
		return builder.toString();
	}
  
}
