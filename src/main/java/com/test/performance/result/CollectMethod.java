package com.test.performance.result;

import com.test.performance.common.RunInfo;
import com.test.performance.stress.ExecuteInfo;

public interface CollectMethod {
	
	boolean prepareEnvironment(RunInfo runInfo);
 	
	void collect(RunInfo runInfo, ExecuteInfo executeInfo, PerformanceResult result);

}
