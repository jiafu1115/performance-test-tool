package com.test.performance.result;

import com.test.performance.common.ExecuteInfo;
import com.test.performance.common.RunInfo;

public interface CollectMethod {
	
	boolean prepareEnvironment(RunInfo runInfo);
 	
	void collect(RunInfo runInfo, ExecuteInfo executeInfo, PerformanceResult result);

}
