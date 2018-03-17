package com.test.performance.result;

public class PerformanceResult {
	
	private String runId;
	private long startTime;
	private String trackingId;
	private long consumeTime;
	private boolean isSuccess;
	private String message;
  	
	public PerformanceResult(String runId, String trackingId, boolean isSuccess, String message, long startTime, long consumeTime) {
		super();
		this.runId = runId;
		this.startTime = startTime;
		this.trackingId = trackingId;
		this.consumeTime = consumeTime;
		this.isSuccess = isSuccess;
		this.message = 	message;
	}
	
	 
	/**
	 * @return the runId
	 */
	public String getRunId() {
		return runId;
	}



	/**
	 * @param runId the runId to set
	 */
	public void setRunId(String runId) {
		this.runId = runId;
	}



	/**
	 * @return the trackingId
	 */
	public String getTrackingId() {
		return trackingId;
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
	 * @param consumeTime the consumeTime to set
	 */
	public void setConsumeTime(long consumeTime) {
		this.consumeTime = consumeTime;
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


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PerformanceResult [runId=");
		builder.append(runId);
		builder.append("&trackingId=");
		builder.append(trackingId);
		builder.append("&isSuccess=");
		builder.append(isSuccess);
		builder.append("&message=");
		builder.append(message);
		builder.append("&startTime=");
		builder.append(startTime);
		builder.append("&consumeTime=");
		builder.append(consumeTime);
		builder.append("]");
		return builder.toString();
	}
 
	
 
}
