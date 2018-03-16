package com.test.performance.result;

import com.test.performance.execute.ExecuteResult;

public class PerformanceResult {
	
	private long startTime;
	private String trackingId;
	private long consumeTime;
	private ExecuteResult executeResult;
 	
	public PerformanceResult(long startTime, String trackingId,  long consumeTime, ExecuteResult executeResult) {
		super();
		this.startTime = startTime;
		this.trackingId = trackingId;
		this.consumeTime = consumeTime;
		this.executeResult = executeResult;
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
	 * @return the executeResult
	 */
	public ExecuteResult getExecuteResult() {
		return executeResult;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	/**
	 * @param executeResult the executeResult to set
	 */
	public void setExecuteResult(ExecuteResult executeResult) {
		this.executeResult = executeResult;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PerformanceResult [startTime=");
		builder.append(startTime);
		builder.append("&trackingId=");
		builder.append(trackingId);
		builder.append("&consumeTime=");
		builder.append(consumeTime);
		builder.append("&executeResult=");
		builder.append(executeResult);
		builder.append("]");
		return builder.toString();
	}
	 
	 
	
 
}
