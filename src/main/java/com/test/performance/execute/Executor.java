package com.test.performance.execute;

import com.test.performance.result.PerformanceResult;

public interface Executor {
	
	public boolean prepare();

	public PerformanceResult execute(String trackingId);

}
