package com.test.performance.common;

public class StressInfo {
	
	private int durationInSeconds;
	private int threadNumber;
	private int tps;
 	 
	public StressInfo(int durationInSeconds, int threadNumber, int tps) {
		super();
		this.durationInSeconds = durationInSeconds;
		this.threadNumber = threadNumber;
		this.tps = tps;
	}
	/**
	 * @return the durationInSeconds
	 */
	public int getDurationInSeconds() {
		return durationInSeconds;
	}
	/**
	 * @return the threadNumber
	 */
	public int getThreadNumber() {
		return threadNumber;
	}
	/**
	 * @return the tps
	 */
	public int getTps() {
		return tps;
	}
	/**
	 * @param durationInSeconds the durationInSeconds to set
	 */
	public void setDurationInSeconds(int durationInSeconds) {
		this.durationInSeconds = durationInSeconds;
	}
	/**
	 * @param threadNumber the threadNumber to set
	 */
	public void setThreadNumber(int threadNumber) {
		this.threadNumber = threadNumber;
	}
	/**
	 * @param tps the tps to set
	 */
	public void setTps(int tps) {
		this.tps = tps;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StressInfo [durationInSeconds=");
		builder.append(durationInSeconds);
		builder.append("&threadNumber=");
		builder.append(threadNumber);
		builder.append("&tps=");
		builder.append(tps);
		builder.append("]");
		return builder.toString();
	}
	 
	 

}
