package com.test.performance.demo;

import com.test.performance.result.CollectMethod;
import com.test.performance.result.PerformanceResult;

public class DefaultCollectMethodImpl implements CollectMethod {

	@Override
	public void collect(PerformanceResult result) {
		if(!result.isSuccess()){
			System.out.println("fail request: " + result);
		}
	}

}
