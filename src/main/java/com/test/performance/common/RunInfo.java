package com.test.performance.common;

public class RunInfo {
	
	private String program;
	private String testName;
	private String runId;
	private String runIp;

	public RunInfo(String program, String testName, String runId, String runIp) {
		this.program = program;
		this.testName = testName;
		this.runId = runId;
		this.runIp = runIp;
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
		builder.append("RunInfo [program=");
		builder.append(program);
		builder.append("&testName=");
		builder.append(testName);
		builder.append("&runId=");
		builder.append(runId);
		builder.append("&runIp=");
		builder.append(runIp);
		builder.append("]");
		return builder.toString();
	}
	
	

}
