package com.test.performance.result.impl;

import com.test.performance.common.RunInfo;
import com.test.performance.result.CollectMethod;
import com.test.performance.result.PerformanceResult;

public class DefaultCollectMethodImpl implements CollectMethod {

	@Override
	public void collect(RunInfo runInfo, PerformanceResult result) {
		if(!result.isSuccess()){
			System.out.println("fail request: " + result);
		}
	}

}
