package com.test.performance.execute;

import com.test.performance.result.PerformanceResult;

public interface TestCaseExecutor {
	
	public boolean prepareEnvironment();

	public PerformanceResult execute(String trackingId);

}
