package com.test.performance.common;

public class RunInfo {
	
	private String program;
	private String testName;
	private String runId;

	public RunInfo(String program, String testName, String runId) {
		this.program = program;
		this.testName = testName;
		this.runId = runId;
	}
 
	/**
	 * @return the program
	 */
	public String getProgram() {
		return program;
	}

 
	public String getTestName() {
		return testName;
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
	public void setTestName(String testName) {
		this.testName = testName;
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
		builder.append("&testName=");
		builder.append(testName);
		builder.append("&runId=");
		builder.append(runId);
		builder.append("]");
		return builder.toString();
	}

}
