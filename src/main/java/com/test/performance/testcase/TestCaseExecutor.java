package com.test.performance.testcase;

import com.test.performance.result.PerformanceResult;

public interface TestCaseExecutor {
	
	public boolean prepareEnvironment();

	public PerformanceResult execute(String trackingId);
	
	public void beforeTest();
	
	public void afterTest();

	public void destoryEnvironment();

}
