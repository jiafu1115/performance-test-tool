package com.test.performance.execute;

import com.test.performance.result.PerformanceResult;

public interface Executor {
	
	public PerformanceResult execute(String trackingId);

}
