package com.test.performance.demo;

import com.test.performance.execute.AbstractExecutor;
import com.test.performance.execute.ExecuteResult;

public class DemoExecutorImpl extends AbstractExecutor {

	@Override
	protected ExecuteResult run(String trackingId) {
		System.out.println("run test case with trackingId:" + trackingId);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
 		return new ExecuteResult();
	}
	 
}
