package com.test.performance.execute;

public class ExecuteResult {
	
	private boolean isSuccess;
	private String message;
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
		builder.append("ExecuteResult [isSuccess=");
		builder.append(isSuccess);
		builder.append("&message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}
	
	

}
