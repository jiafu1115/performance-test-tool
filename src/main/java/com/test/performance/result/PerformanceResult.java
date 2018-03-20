package com.test.performance.result;

public class PerformanceResult {
	
	private long startTime;
	private String trackingId;
	private long duration;
	private boolean isSuccess;
	private String message;
  	
	public PerformanceResult(String trackingId, boolean isSuccess, String message, long startTime, long duration) {
		this.startTime = startTime;
		this.trackingId = trackingId;
		this.duration = duration;
		this.isSuccess = isSuccess;
		this.message = 	message;
	}
  
	/**
	 * @return the duration
	 */
	public long getDuration() {
		return duration;
	}
 
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(long duration) {
		this.duration = duration;
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
		builder.append("&duration=");
		builder.append(duration);
		builder.append("]");
		return builder.toString();
	}
  
}
