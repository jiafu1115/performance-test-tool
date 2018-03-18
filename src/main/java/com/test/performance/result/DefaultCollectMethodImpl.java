package com.test.performance.result;

import com.test.performance.common.RunInfo;

public class DefaultCollectMethodImpl implements CollectMethod {

	@Override
	public void collect(RunInfo runInfo, PerformanceResult result) {
		if(!result.isSuccess()){
			System.out.println("fail request: " + result);
		}
	}

}
