package com.test.performance.demo;

import com.test.performance.result.CollectMethod;
import com.test.performance.result.PerformanceResult;

public class DemoCollectMethodImpl implements CollectMethod {

	@Override
	public void collect(PerformanceResult result) {
		System.out.println("        send to report system: " + result);
	}

}
