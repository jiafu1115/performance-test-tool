package com.test.performance.demo;

import com.test.performance.testcase.AbstractTestCaseExecutor;
import com.test.performance.testcase.TestCaseResult;

public class DemoTestCaseImpl extends AbstractTestCaseExecutor {

	@Override
	protected TestCaseResult run(String trackingId) {
		System.out.println("run test case with trackingId:" + trackingId);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
 		return new TestCaseResult();
	}
	 
}
