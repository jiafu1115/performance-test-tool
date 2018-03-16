package com.test.performance.demo;

import com.test.performance.execute.AbstractExecutor;

public class DemoExecutorImpl extends AbstractExecutor {

	@Override
	protected boolean run(String trackingId) {
		System.out.println("run test case with trackingId:" + trackingId);
 		return true;
	}

}
