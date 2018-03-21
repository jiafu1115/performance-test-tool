package com.test.performance.result.impl;

import com.test.performance.common.RunInfo;
import com.test.performance.result.CollectMethod;
import com.test.performance.result.PerformanceResult;
import com.test.performance.stress.ExecuteInfo;

public class DefaultCollectMethodImpl implements CollectMethod {

	
	@Override
	public boolean prepareEnvironment(RunInfo runInfo) {
		return true;
 	}
	
	@Override
	public void collect(RunInfo runInfo, ExecuteInfo executeInfo, PerformanceResult result) {
		if(!result.isSuccess()){
			System.out.println("fail request: " + result);
		}
	}



}
