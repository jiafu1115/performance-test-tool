package com.test.performance.stress;

public class ExecuteInfo {
	
	private long threadId;
	
	public ExecuteInfo(long threadId) {
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExecuteInfo [threadId=");
		builder.append(threadId);
		builder.append("]");
		return builder.toString();
	}
	
	

}
