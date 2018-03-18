package com.test.performance.result;

public class DefaultCollectMethodImpl implements CollectMethod {

	@Override
	public void collect(String program, String runId, PerformanceResult result) {
		if(!result.isSuccess()){
			System.out.println("fail request: " + result);
		}
	}

}
