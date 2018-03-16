package com.test.performance;

public class TestExecutorImpl extends AbstractExecutor {

	@Override
	boolean run(String trackingId) {
		System.out.println("run test case with trackingId:" + trackingId);
 		return true;
	}

}
