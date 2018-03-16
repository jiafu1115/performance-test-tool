package com.test.performance.demo;

import com.test.performance.result.CollectMethod;
import com.test.performance.result.Result;

public class DemoCollectMethodImpl implements CollectMethod {

	@Override
	public void collect(Result result) {
		System.out.println("send to report system: " + result);
	}

}
