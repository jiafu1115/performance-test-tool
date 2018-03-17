package com.test.performance.testcase;

public class TestCaseResult {
	
	private boolean isSuccess = true;
	private String message = "";
	 
	public TestCaseResult(boolean isSuccess, String message) {
		this.isSuccess = isSuccess;
		this.message = message;
	}
	
	public TestCaseResult(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	public TestCaseResult(){
		
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
		builder.append("ExecuteResult [isSuccess=");
		builder.append(isSuccess);
		builder.append("&message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}
	
	

}
