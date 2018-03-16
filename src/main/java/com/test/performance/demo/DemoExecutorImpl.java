package com.test.performance.demo;

import com.test.performance.execute.AbstractExecutor;
import com.test.performance.execute.ExecuteResult;

public class DemoExecutorImpl extends AbstractExecutor {

	@Override
	protected ExecuteResult run(String trackingId) {
		System.out.println("run test case with trackingId:" + trackingId);
 		return new ExecuteResult();
	}

}
