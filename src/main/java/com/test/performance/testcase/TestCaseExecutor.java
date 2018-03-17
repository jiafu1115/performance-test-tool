package com.test.performance.testcase;

import com.test.performance.result.PerformanceResult;

public interface TestCaseExecutor {
	
	public boolean prepareEnvironment();

	public PerformanceResult execute(String runId, String trackingId);

}
