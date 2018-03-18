package com.test.performance.common;

public class RunInfo {
	
	private String program;
	private String testItem;
	private String runId;
	
	public RunInfo(String program, String testItem, String runId) {
		super();
		this.program = program;
		this.testItem = testItem;
		this.runId = runId;
	}

	/**
	 * @return the program
	 */
	public String getProgram() {
		return program;
	}

	/**
	 * @return the testItem
	 */
	public String getTestItem() {
		return testItem;
	}

	/**
	 * @return the runId
	 */
	public String getRunId() {
		return runId;
	}

	/**
	 * @param program the program to set
	 */
	public void setProgram(String program) {
		this.program = program;
	}

	/**
	 * @param testItem the testItem to set
	 */
	public void setTestItem(String testItem) {
		this.testItem = testItem;
	}

	/**
	 * @param runId the runId to set
	 */
	public void setRunId(String runId) {
		this.runId = runId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RunInfo [program=");
		builder.append(program);
		builder.append("&testItem=");
		builder.append(testItem);
		builder.append("&runId=");
		builder.append(runId);
		builder.append("]");
		return builder.toString();
	}
	
	

}
