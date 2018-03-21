package com.test.performance.common;

public class ExecuteInfo {
	
	private long threadId;
	private String runIp;


	public ExecuteInfo(String runIp, long threadId) {
		this.runIp = runIp;
		this.threadId = threadId;
	}

	/**
	 * @return the threadId
	 */
	public long getThreadId() {
		return threadId;
	}

	/**
	 * @param threadId the threadId to set
	 */
	public void setThreadId(long threadId) {
		this.threadId = threadId;
	}

	
	
	/**
	 * @return the runIp
	 */
	public String getRunIp() {
		return runIp;
	}

	/**
	 * @param runIp the runIp to set
	 */
	public void setRunIp(String runIp) {
		this.runIp = runIp;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExecuteInfo [threadId=");
		builder.append(threadId);
		builder.append("&runIp=");
		builder.append(runIp);
		builder.append("]");
		return builder.toString();
	}
	

}
